package com.grasset.fernando.tp3_oo_lependu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class YouLoseActivity extends AppCompatActivity {

    String word;
    TextView whatWasThatWord;
    Button resetGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_lose);

        word = getIntent().getStringExtra("word");
        whatWasThatWord = findViewById(R.id.whatWasThatWord);

        whatWasThatWord.setText(word);

        resetGameButton = findViewById(R.id.resetGameButton);
        resetGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGameActivity();
            }
        });


    }
    private void NewGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
