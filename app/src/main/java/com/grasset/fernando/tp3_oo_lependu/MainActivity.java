package com.grasset.fernando.tp3_oo_lependu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newGameButton,changeServerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = findViewById(R.id.newGameButton);
        changeServerButton = findViewById(R.id.changeServerButton);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity();
            }
        });

        changeServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeServerActivity();
            }
        });
    }
    private void GameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    private void ChangeServerActivity(){
        Intent intent = new Intent(this, ChangeServerActivity.class);
        startActivity(intent);
    }
}
