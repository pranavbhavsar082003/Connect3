package com.example.assignmenttwo;


public class GameModel {
    private int[] gameState;
    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public GameModel() {
        gameState = new int[9];
        resetGame();
    }

    public void resetGame() {
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }
    }

    public boolean isValidMove(int position) {
        return gameState[position] == 0;
    }

    public void makeMove(int position, int player) {
        gameState[position] = player;
    }

    public boolean checkWin() {
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] != 0 &&
                    gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]) {
                return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int state : gameState) {
            if (state == 0) {
                return false;
            }
        }
        return true;
    }
}

