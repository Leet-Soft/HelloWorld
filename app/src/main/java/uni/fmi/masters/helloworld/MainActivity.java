package uni.fmi.masters.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView greetingsTV;
    EditText nameET;
    Button sayHelloB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingsTV = findViewById(R.id.greetingsTextView);
        nameET = findViewById(R.id.nameEditText);
        sayHelloB = findViewById(R.id.okButton);

        sayHelloB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                greetingsTV.setText("Hello " + name);
                nameET.setText("");
            }
        });
    }


}