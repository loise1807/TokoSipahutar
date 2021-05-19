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
import del.proyek_akhir2.sipahutarstore.model.register.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etRegisterName, etRegisterUsername, etRegisterPassword, etRegisterAlamat, etRegisterJenisKelamin, etRegisterNoTlp;
    Button btnRegister;
    TextView tvLogin;
    String regName, regUsername, regPassword,regAlamat,regJenisKelamin,regNoTlp;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterName = findViewById(R.id.etRegisterName);
        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterAlamat = findViewById(R.id.etRegisterAlamat);
        etRegisterJenisKelamin = findViewById(R.id.etRegisterJenisKelamin);
        etRegisterNoTlp = findViewById(R.id.etRegisterNoTlp);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLoginAccount);
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                regName = etRegisterName.getText().toString();
                regUsername = etRegisterUsername.getText().toString();
                regPassword = etRegisterPassword.getText().toString();
                regJenisKelamin = etRegisterJenisKelamin.getText().toString();
                regAlamat = etRegisterAlamat.getText().toString();
                regNoTlp = etRegisterNoTlp.getText().toString();
                register(regName,regUsername,regPassword,regJenisKelamin,regAlamat,regNoTlp);
                break;
            case R.id.tvLoginAccount:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void register(String regName, String regUsername, String regPassword, String regJenisKelamin, String regAlamat, String regNoTlp) {

        apiInterface = apiclient.getClient().create(ApiInterface.class);
        Call<Register> loginCall = apiInterface.registerResponse(regName,regUsername,regPassword,regJenisKelamin,regAlamat,regNoTlp);
        loginCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}