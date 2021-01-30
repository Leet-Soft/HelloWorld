package uni.fmi.masters.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    Button loginB;
    TextView registerTV;

    SharedPreferences pref;
    String username;
    String password;

    SQLiteDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new SQLiteDatabaseHelper(this);

        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        loginB = findViewById(R.id.loginButton);
        registerTV = findViewById(R.id.registerTextView);

        loginB.setOnClickListener(onClick);
        registerTV.setOnClickListener(onClick);

        //pref = getApplicationContext().getSharedPreferences(RegisterActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        //username = pref.getString(RegisterActivity.SHARED_PREF_USERNAME, "goshko");
        //password = pref.getString(RegisterActivity.SHARED_PREF_PASSWORD, "1111");
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.loginButton){

                String usernameInput = usernameET.getText().toString();
                String passwordInput = passwordET.getText().toString();

               // if(usernameInput.equalsIgnoreCase(username) && passwordInput.equals(password)){
                 if(dbHelper.login(usernameInput, passwordInput)){
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    intent.putExtra("user", usernameInput);

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "HACKER GO AWAY!!!", Toast.LENGTH_SHORT).show();
                }

            }else{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }
    };
}