package com.example.muath.guessgame;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GuessGameActivity extends AppCompatActivity {

    private TextView tv_message;
    private ImageView iv_image;
    private EditText et_guess;
    private Button bt_again;
    private Button bt_check;
    private Button bt_language;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeView();

    }

    private void initializeView() {
        setContentView(R.layout.activity_guess_game);

        tv_message = findViewById(R.id.tv_message);
        iv_image = findViewById(R.id.iv_image);
        et_guess = findViewById(R.id.et_guess);
        bt_again = findViewById(R.id.bt_play);
        bt_check = findViewById(R.id.bt_check);
        bt_language = findViewById(R.id.bt_lag);
        number = (int)(Math.random() * 100);

        bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkGuess();
            }
        });


        bt_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        bt_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage();
            }
        });

        et_guess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return checkGuess();

            }
        });
    }

    private boolean checkGuess(){

        System.out.println("GuessGameActivity.checkGuess");

        int guess = Integer.parseInt(et_guess.getText().toString());

        System.out.println(number);

        if (guess > number){
            Toast.makeText(this,getString(R.string.toast_smaller), Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (guess < number){
            Toast.makeText(this,getString(R.string.toast_greater), Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            tv_message.setText(getString(R.string.tv_message_right));
            iv_image.setImageResource(R.drawable.twitter37);
            return false;
        }
    }

    private void playAgain(){
        System.out.println("GuessGameActivity.playAgain");
        number = (int)(Math.random() * 100);

        System.out.println(number);

        tv_message.setText(R.string.tv_message_def);
        iv_image.setImageResource(R.drawable.bird);
        et_guess.setText(" ");
    }

    private void changeLanguage(){
        System.out.println("GuessGameActivity.changeLanguage");

        Configuration config = getResources().getConfiguration();
        String language = bt_language.getText().toString().toLowerCase();
        config.setLocale(new Locale(language));
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());
        initializeView();

    }



}
