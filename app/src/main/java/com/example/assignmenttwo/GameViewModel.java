package com.example.assignmenttwo;


import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {
    private GameModel gameModel;

    public GameViewModel() {
        gameModel = new GameModel();
    }

    public boolean isValidMove(int position) {
        return gameModel.isValidMove(position);
    }

    public void makeMove(int position, int player) {
        gameModel.makeMove(position, player);
    }

    public boolean checkWin() {
        return gameModel.checkWin();
    }

    public boolean isBoardFull() {
        return gameModel.isBoardFull();
    }

    public void resetGame() {
        gameModel.resetGame();
    }
}
