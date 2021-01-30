package uni.fmi.masters.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView triesTV;
    TextView hintTV;
    TextView numbersTV;
    EditText numberET;
    Button okB;

    int tries = 5;
    int numberToGuess;
    Random random = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        triesTV = findViewById(R.id.triesTextView);
        hintTV = findViewById(R.id.hintTextVIew);
        numbersTV = findViewById(R.id.numbersTextView);
        numberET = findViewById(R.id.numberEditText);
        okB = findViewById(R.id.okButton);

        numberToGuess = random.nextInt(101);
        okB.setOnClickListener(onClick);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (numberET.getText().length() == 0){
                return;
            }


            tries--;

            int numberByUser = Integer.parseInt(numberET.getText().toString());

            if(numberByUser > numberToGuess){
                hintTV.setText("▼");
            }else if(numberByUser < numberToGuess){
                hintTV.setText("▲");
            }else{
                hintTV.setText("Winner!");
                okB.setEnabled(false);
                return;
            }

            numbersTV.append(String.valueOf(numberByUser) + " ");
            triesTV.setText("Gusses left: " + tries);
            numberET.setText("");

            if(tries == 0){
                hintTV.setText("Loser!");
                triesTV.setText("The number was " + numberToGuess);
                okB.setEnabled(false);
                return;
            }


        }
    };

}