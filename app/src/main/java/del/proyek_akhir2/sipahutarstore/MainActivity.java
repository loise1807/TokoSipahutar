package del.proyek_akhir2.sipahutarstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    TextView etUsername,etName;
    String getName, getUsername;

    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment f = null;
            switch (item.getItemId()){
                case R.id.menu_dashboard:
                    f = new FragmentDashboard();
                    break;
                case R.id.menu_pesanan:
                    f = new FragmentPesanan();
                    break;
                case R.id.menu_profile:
                    f = new FragmentProfile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,f).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        bottomNavigationView = findViewById(R.id.btn_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);

        etUsername = findViewById(R.id.etMainUsername);
        etName = findViewById(R.id.etMainName);

        getUsername = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        getName = sessionManager.getUserDetail().get(SessionManager.NAME);

        etUsername.setText(getUsername);
        etName.setText(getName);

    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                sessionManager.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}