package com.example.oladapo.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int score = 0;
    int numberOfQuestions = 0;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(3100, 1000) {

            @Override
            public void onTick(long millisUntilFinished){

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }

        }.start();

    }

    public void generateQusetion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer (View view) {

            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

                score++;
                resultTextView.setText("Correct!");

            } else {

                resultTextView.setText("Wrong!");

            }

            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generateQusetion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
            }
        });

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        generateQusetion();

        playAgain(findViewById(R.id.playAgainButton));

    }
}
