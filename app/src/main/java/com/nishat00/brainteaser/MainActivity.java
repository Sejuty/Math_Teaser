package com.nishat00.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer count;
    TextView timer;
    int rightLocation;
    int c = 0;
    int numberOfQues = 0;
    TextView rightWrong;
    TextView scoreText;
    Button button0, button1, button2, button3, goButton;
    ImageButton playAgainButton;
    TextView equationTextView;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<>();


    /*here gridLayout will be the default layout.  after clicking the answer the chooseAnswer() will be triggered.
    then random ques will be created with newQues() function.
     */
    public void playAgain(View v) {
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        c = 0;
        numberOfQues = 0;
        scoreText.setText(Integer.toString(c) + " / " + Integer.toString(numberOfQues));
        timer.setText("30s");
        newQues();
        count = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(Integer.toString((int) l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timer.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }


    public void chooseAnswer(View v) {
        if (v.getTag().toString().equals(Integer.toString(rightLocation))) {
            c++;
            rightWrong.setText("Correct :D");
        } else {
            rightWrong.setText("WRONG :(");
        }
        numberOfQues++;
        scoreText.setText(Integer.toString(c) + " / " + Integer.toString(numberOfQues));
        newQues();
    }

    public void newQues() {
        Random random = new Random();
        int num_1 = random.nextInt(81);
        int num_2 = random.nextInt(81);

        equationTextView.setText(Integer.toString(num_1) + " + " + Integer.toString(num_2));

        //generate a random location
        rightLocation = random.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            //when the random location is generated add right answer to the array location
            if (i == rightLocation)
                answers.add(num_1 + num_2);
            else {
                //in other location add any random number
                int wrongAnswer = random.nextInt(170);
                while (wrongAnswer == num_1 + num_2)
                    wrongAnswer = random.nextInt(170);
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void go(View v) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(equationTextView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationTextView = findViewById(R.id.mathTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        rightWrong = findViewById(R.id.rightWrong);
        scoreText = findViewById(R.id.scoreTextView);
        timer = findViewById(R.id.timer);
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}