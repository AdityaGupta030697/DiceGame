package com.example.aditya.dicegame;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int[] images = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6, R.drawable.dice1};
    Button roll, hold, reset;
    ImageView dice;
    private int user_score_overall;
    private int user_score;
    private int computer_score_overall;
    private int computer_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        dice = (ImageView) findViewById(R.id.dice);
        final Random random = new Random();

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int index = random.nextInt(6);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //>= API 21
                    dice.setImageDrawable(getResources().getDrawable(images[index], getApplicationContext().getTheme()));
                } else {
                    dice.setImageDrawable(getResources().getDrawable(images[index]));
                }

                Log.v("TAGGER", index + "");


            }
        });


    }
}
