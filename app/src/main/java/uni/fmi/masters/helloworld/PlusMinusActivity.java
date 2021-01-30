package uni.fmi.masters.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlusMinusActivity extends AppCompatActivity {

    TextView resultTV;
    EditText numberET;
    Button plusB;
    Button minusB;
    Button divideB;
    Button multiplyB;

    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_minus);

        resultTV = findViewById(R.id.resultTextView);
        numberET = findViewById(R.id.numberEditText);
        plusB = findViewById(R.id.plusButton);
        minusB = findViewById(R.id.minusButton);
        divideB = findViewById(R.id.dividyButton);
        multiplyB = findViewById(R.id.multiplyButton);

        plusB.setOnClickListener(onButtonClick);
        minusB.setOnClickListener(onButtonClick);
        divideB.setOnClickListener(onButtonClick);
        multiplyB.setOnClickListener(onButtonClick);
    }

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String numberAsString = numberET.getText().toString();

            if(numberAsString.length() == 0){
                Toast.makeText(PlusMinusActivity.this, "Please enter number!", Toast.LENGTH_SHORT).show();
                return;
            }

            double numberByUser = Double.parseDouble(numberAsString);

            switch (v.getId()){
                case R.id.plusButton:
                    result += numberByUser;
                    break;
                case R.id.minusButton:
                    result -= numberByUser;
                    break;
                case R.id.dividyButton:
                    if(numberByUser != 0)
                        result /= numberByUser;
                    else
                        Toast.makeText(PlusMinusActivity.this,
                                "Cannot divide by 0", Toast.LENGTH_LONG).show();
                    break;
                case R.id.multiplyButton:
                    result *= numberByUser;
                    break;
            }

            resultTV.setText("Result:" + result);
            numberET.setText("");

        }
    };
}