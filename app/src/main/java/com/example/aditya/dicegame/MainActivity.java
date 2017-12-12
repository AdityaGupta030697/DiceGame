package com.example.aditya.dicegame;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int[] images = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6, R.drawable.dice1};
    Button roll, hold, reset;
    ImageView dice;
    TextView score;
    private int user_score_overall = 0;
    private int user_score = 0;
    private int computer_score_overall = 0;
    private int computer_score = 0;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        dice = (ImageView) findViewById(R.id.dice);
        score = (TextView) findViewById(R.id.score_label);
        final Random random = new Random();

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index = random.nextInt(6);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //>= API 21
                    dice.setImageDrawable(getResources().getDrawable(images[index], getApplicationContext().getTheme()));
                } else {
                    dice.setImageDrawable(getResources().getDrawable(images[index]));
                }

                index++;

                if (index == 1) {
                    user_score = 0;
                    score.setText("Your score: " + user_score_overall + "computer score: " + computer_score_overall + " your turn score: " + user_score);

                } else {

                    user_score += index;
                    score.setText("Your score: " + user_score_overall + "computer score: " + computer_score_overall + " your turn score: " + user_score);


                }


            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_score_overall += user_score;
                user_score = 0;
                score.setText("Your score: " + user_score_overall + "computer score: " + computer_score_overall + " your turn score: " + user_score);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_score_overall = 0;
                user_score = 0;
                computer_score_overall = 0;
                computer_score = 0;
                score.setText("Your score: " + user_score_overall + "computer score: " + computer_score_overall + " your turn score: " + user_score);

            }
        });


    }


}
