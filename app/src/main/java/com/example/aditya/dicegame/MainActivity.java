package com.example.aditya.dicegame;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        dice = (ImageView) findViewById(R.id.dice);
        score = (TextView) findViewById(R.id.score_label);
        random = new Random();

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int index = rollDice();
                if (index == 1) {
                    user_score = 0;
                    score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\nyour turn score: " + user_score);
                    ComputerTurn();
                } else {
                    user_score += index;
                    score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\nyour turn score: " + user_score);
                    if (user_score + user_score_overall >= 100) {
                        win(0);
                    }
                }


            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_score_overall += user_score;
                user_score = 0;
                score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\nyour turn score: " + user_score);
                ComputerTurn();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll.setEnabled(true);
                hold.setEnabled(true);
                user_score_overall = 0;
                user_score = 0;
                computer_score_overall = 0;
                computer_score = 0;
                score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\nyour turn score: " + user_score);

            }
        });


    }

    public int rollDice() {
        int index = random.nextInt(6);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //>= API 21
            dice.setImageDrawable(getResources().getDrawable(images[index], getApplicationContext().getTheme()));
        } else {
            dice.setImageDrawable(getResources().getDrawable(images[index]));
        }

        index++;

        return index;

    }

    public void ComputerTurn() {

        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                int index = rollDice();
                if (index == 1) {
                    computer_score = 0;
                    score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\ncomputer turn score: " + computer_score);
                    userChance();
                } else {
                    computer_score += index;
                    if (computer_score + computer_score_overall >= 100) {
                        win(1);
                        return;
                    }
                    score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\ncomputer turn score: " + computer_score);
                    if (computer_score > 20) {
                        computer_score_overall += computer_score;
                        computer_score = 0;
                        score.setText("Your score: " + user_score_overall + "\ncomputer score: " + computer_score_overall + "\ncomputer turn score: " + computer_score);
                        userChance();


                    } else {
                        ComputerTurn();
                    }
                }


            }
        }.start();
    }

    // Enable all the buttons
    private void userChance() {
        roll.setEnabled(true);
        hold.setEnabled(true);
        reset.setEnabled(true);
    }

    private void win(int flag) {
        if (flag == 0) {
            score.setText("YOU WON!!!!!");
        } else {
            score.setText("COMPUTER WON!!!!");
        }
        userChance();

    }


}
