package uni.fmi.masters.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button helloB;
    Button gameB;
    Button calculatorB;
    Button logoutB;
    Button deleteB;
    TextView helloTV;

    SQLiteDatabaseHelper dbHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helloB = findViewById(R.id.helloButton);
        gameB = findViewById(R.id.gameButton);
        calculatorB = findViewById(R.id.calculatorButton);
        logoutB = findViewById(R.id.logoutButton);
        deleteB = findViewById(R.id.deleteButton);
        helloTV = findViewById(R.id.helloTextVIew);

        helloB.setOnClickListener(onClick);
        gameB.setOnClickListener(onClick);
        calculatorB.setOnClickListener(onClick);
        deleteB.setOnClickListener(onClick);
        logoutB.setOnClickListener(onClick);

        username = getIntent().getStringExtra("user");
        helloTV.setText("Hello " + username);
        dbHelper = new SQLiteDatabaseHelper(this);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = null;

            switch (v.getId()){
                case R.id.helloButton:
                    intent = new Intent(MenuActivity.this, MainActivity.class);
                    break;
                case R.id.gameButton:
                    intent = new Intent(MenuActivity.this, GameActivity.class);
                    break;
                case R.id.calculatorButton:
                    intent = new Intent(MenuActivity.this, PlusMinusActivity.class);
                    break;
                case R.id.logoutButton:
                    finish();
                    break;
                case R.id.deleteButton:
                    deleteDialog();
                    break;
            }

            if(intent != null)
                startActivity(intent);
        }
    };

    private void deleteDialog(){
        final Dialog dialog = new Dialog(MenuActivity.this);
        dialog.setTitle("Are you sure?");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_delete_dialog);

        Button okButton = dialog.findViewById(R.id.okButton);
        Button cancelButton = dialog.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Deleting in progress.....", Toast.LENGTH_LONG).show();
                dbHelper.deleteAccount(username);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}