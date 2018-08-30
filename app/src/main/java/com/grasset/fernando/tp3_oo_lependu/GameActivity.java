package com.grasset.fernando.tp3_oo_lependu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameActivity extends AppCompatActivity {

    TextView myWordToGuess, myGuess,lettersThatIHaveGuessed,myQuantityErrors;
    String myLastWord = "";
    ArrayList<String> myLettres = new ArrayList<>();
    Integer quantityError = 0;
    ImageView myImageHangMan;

    GameThread thread;
    private BlockingQueue<String> blockingQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myWordToGuess = findViewById(R.id.myWordToGuess);
        myGuess = findViewById(R.id.myGuess);
        lettersThatIHaveGuessed = findViewById(R.id.lettersThatIHaveGuessed);
        lettersThatIHaveGuessed.setText("");
        myQuantityErrors = findViewById(R.id.myQuantityErrors);
        myImageHangMan = (ImageView) findViewById(R.id.myImageHangMan);

        blockingQueue = new LinkedBlockingQueue<>();

        thread = new GameThread(this, blockingQueue);
        thread.start();

        myGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String letter = editable.toString();
                if (!letter.isEmpty() && letter.matches("[a-zA-Z]")) {
                    blockingQueue.add(letter);
                }
                if(editable.toString().matches("[^a-zA-Z]")){
                    editable.clear();
                }
            }
        });
    }

    public void setText(final TextView text, final String value) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        myWordToGuess.setText(value);
                        showMyGuessedLetters(myGuess.getText().toString());
                        countMyErrors(value);
                        displayMyErrors();
                        changeImage(quantityError);
                        checkGameCondition(value);
                        myGuess.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void checkGameCondition(String word) {
        if(quantityError == 6) {
            Intent intent = new Intent(this, YouLoseActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);
            finish();
        } else if (quantityError < 6 && !word.contains("_")) {
            Intent intent = new Intent(this, YouWinActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);
            finish();
        }
    }
    private void showMyGuessedLetters(String a) {
        a = a.toUpperCase();
        if(a.matches("[^a-zA-Z]")) {
            return;
        }
        if(lettersThatIHaveGuessed.getText().toString().contains(a)) {
            return;
        } else {
            if (lettersThatIHaveGuessed.getText().toString().length() == 0) {
                lettersThatIHaveGuessed.append(a);
            } else {
                lettersThatIHaveGuessed.append("-" + a);
            }
        }
        myLettres.add(a);
    }
    private void displayMyErrors() {
        myQuantityErrors.setText(quantityError.toString());
    }
    private void countMyErrors(String mask){
        if(quantityError==6) {
            return;
        }
        quantityError = 0;
        for(int i = 0; i < myLettres.size(); i++) {
            if(!mask.contains(myLettres.get(i))) {
                quantityError++;
            }
        }
    }
    private void changeImage(Integer errors) {
        switch (errors) {
            case (1):
                myImageHangMan.setImageResource(R.drawable.hangman_1);
                break;
            case (2):
                myImageHangMan.setImageResource(R.drawable.hangman_2);
                break;
            case (3):
                myImageHangMan.setImageResource(R.drawable.hangman_3);
                break;
            case (4):
                myImageHangMan.setImageResource(R.drawable.hangman_4);
                break;
            case (5):
                myImageHangMan.setImageResource(R.drawable.hangman_5);
                break;
            case (6):
                myImageHangMan.setImageResource(R.drawable.hangman_6);
                break;
            default:
                myImageHangMan.setImageResource(R.drawable.hangman_0);
        }
    }
}