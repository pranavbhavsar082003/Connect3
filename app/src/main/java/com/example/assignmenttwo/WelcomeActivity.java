package com.example.assignmenttwo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText player1EditText;
    private EditText player2EditText;
    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        player1EditText = findViewById(R.id.player1EditText);
        player2EditText = findViewById(R.id.player2EditText);
        startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Name = player1EditText.getText().toString().trim();
                String player2Name = player2EditText.getText().toString().trim();

                if (player1Name.isEmpty() || player2Name.isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "Please enter names for both players", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);
                    intent.putExtra("PLAYER_1_NAME", player1Name);
                    intent.putExtra("PLAYER_2_NAME", player2Name);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
