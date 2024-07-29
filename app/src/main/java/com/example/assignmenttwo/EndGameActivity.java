package com.example.assignmenttwo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {

    private TextView endGameMessageTextView;
    private Button playAgainButton;
    private Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        endGameMessageTextView = findViewById(R.id.endGameMessageTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        quitButton = findViewById(R.id.quitButton);

        String message = getIntent().getStringExtra("END_GAME_MESSAGE");
        endGameMessageTextView.setText(message);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, GameActivity.class);
                intent.putExtra("PLAYER_1_NAME", getIntent().getStringExtra("PLAYER_1_NAME"));
                intent.putExtra("PLAYER_2_NAME", getIntent().getStringExtra("PLAYER_2_NAME"));
                startActivity(intent);
                finish();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
