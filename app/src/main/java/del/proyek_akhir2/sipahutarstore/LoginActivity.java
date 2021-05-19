package del.proyek_akhir2.sipahutarstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import del.proyek_akhir2.sipahutarstore.api.ApiInterface;
import del.proyek_akhir2.sipahutarstore.api.apiclient;
import del.proyek_akhir2.sipahutarstore.model.login.Login;
import del.proyek_akhir2.sipahutarstore.model.login.LoginData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUsername, etPassword;
    Button btnLogin;
    String Username,Password;
    TextView tvRegister;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tvCreateAccount);
        tvRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Username = etUsername.getText().toString();
                Password = etPassword.getText().toString();

                if (Username.matches("") && Password.matches("")){
                    Toast.makeText(this,"Username dan Passowrd harus di isi", Toast.LENGTH_SHORT).show();
                }else if (Username.matches("")){
                    Toast.makeText(this,"Username harus di isi", Toast.LENGTH_SHORT).show();
                }else if (Password.matches("")){
                    Toast.makeText(this,"Password harus di isi", Toast.LENGTH_SHORT).show();
                }else{
                    login(Username,Password);
                }
                break;
            case R.id.tvCreateAccount:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String username, String password) {

        apiInterface = apiclient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // menyimpan sesi
                    sessionManager = new SessionManager(LoginActivity.this);
                    LoginData loginData = response.body().getLoginData();
                    sessionManager.createLoginSession(loginData);

                    // untuk pindah
                    Toast.makeText(LoginActivity.this, response.body().getLoginData().getNama(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}