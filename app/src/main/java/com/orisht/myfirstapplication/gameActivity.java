package com.orisht.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class gameActivity extends AppCompatActivity {


    EditText yournum;
    Button ok, send,restart;
    TextView result;
    int gamenum, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        initViews();
    }

    private void initViews() {

        yournum = findViewById(R.id.yourNumber);
        ok = findViewById(R.id.okbtn);
        send = findViewById(R.id.numbtn);
        result = findViewById(R.id.result);
        restart = findViewById(R.id.restartbtn);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected range from the RadioGroup
                RadioGroup radioGroup = findViewById(R.id.radioGroupRange);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                // Check if a radio button is selected
                if (selectedRadioButtonId == -1) {
                    result.setText("Please select a range.");
                    return;
                }
                radioGroup.setEnabled(false);
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String range = selectedRadioButton.getText().toString();

                // הופך את הטווח מסטרינג למספר
                int first = 0;
                int second = 0;
                if (range.equals("1-10")) {
                    first = 1;
                    second = 10;
                } else if (range.equals("1-100")) {
                    first = 1;
                    second = 100;
                } else if (range.equals("1-1000")) {
                    first = 1;
                    second = 1000;
                }

                // המחשב בוחר מספר רנדומלי
                gamenum = (int) (Math.random() * ((second - first) + 1)) + first;

                // כמות הנסיונות
                count = 0;

                // Inform the user that the game has started
                result.setText("A random number has been generated. Try to guess it!");

            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // בודק שהוא באמת בחר מספר
                String userInput = yournum.getText().toString();
                if (userInput.isEmpty()) {
                    result.setText("Please enter a number.");
                    return;
                }

                // הופך לint
                int your = Integer.parseInt(userInput);
                count++;  // Increment the attempt counter
                if (gamenum != 0) {
                    // בודק אם המספר שבחר הוא נכון
                    if (your == gamenum) {
                        result.setText("Correct! You won in " + count + " tries.");
                        Intent intent= new Intent();
                        intent.putExtra("num_guesses",count);
                        setResult(RESULT_OK,intent);
                        finish();

                    } else if (your > gamenum) {
                        result.setText("Wrong. The number is too big.");
                    } else {
                        result.setText("Wrong. The number is too small.");
                    }
                }
                else {
                    result.setText("you need to pick a range");
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Result will appear here");


                yournum.setText("");


                count = 0;


                gamenum = 0;
                RadioGroup radioGroup = findViewById(R.id.radioGroupRange);
                radioGroup.clearCheck();

            }
        });
    }
}