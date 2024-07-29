package com.example.assignmenttwo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private String player1Name;
    private String player2Name;
    private boolean isPlayer1Turn = true;
    private TextView currentPlayerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1Name = getIntent().getStringExtra("PLAYER_1_NAME");
        player2Name = getIntent().getStringExtra("PLAYER_2_NAME");

        currentPlayerTextView = findViewById(R.id.currentPlayerTextView);
        updateCurrentPlayerText();

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        setupBoard();
    }

    private void setupBoard() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < 9; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(0);
            imageView.setTag(i);
            imageView.setOnClickListener(this::dropIn);
            imageView.setBackgroundResource(R.drawable.grid_border);


            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i % 3, 1f);
            params.rowSpec = GridLayout.spec(i / 3, 1f);
            params.setMargins(4, 4, 4, 4);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            gridLayout.addView(imageView);
        }
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = (int) counter.getTag();

        if (gameViewModel.isValidMove(tappedCounter)) {
            counter.setTranslationY(-1500);
            counter.setImageResource(isPlayer1Turn ? R.drawable.fire : R.drawable.water);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            gameViewModel.makeMove(tappedCounter, isPlayer1Turn ? 1 : 2);

            if (gameViewModel.checkWin()) {
                String winner = isPlayer1Turn ? player1Name : player2Name;
                navigateToEndGame("Congratulations " + winner + "!!!");
            } else if (gameViewModel.isBoardFull()) {
                navigateToEndGame("It's a tie!!");
            }

            isPlayer1Turn = !isPlayer1Turn;
            updateCurrentPlayerText();
        }
    }

    private void updateCurrentPlayerText() {
        String currentPlayer = isPlayer1Turn ? player1Name : player2Name;
        currentPlayerTextView.setText(currentPlayer + "'s turn");
    }

    private void navigateToEndGame(String message) {
        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
        intent.putExtra("END_GAME_MESSAGE", message);
        intent.putExtra("PLAYER_1_NAME", player1Name);
        intent.putExtra("PLAYER_2_NAME", player2Name);
        startActivity(intent);
        finish();
    }
}
