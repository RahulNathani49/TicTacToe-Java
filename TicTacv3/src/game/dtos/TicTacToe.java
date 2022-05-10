package game.dtos;

import java.io.Serializable;

public class TicTacToe implements Serializable {

    private int playerTurn;
    private int index;

    private boolean hasWon;

    public TicTacToe() {
        playerTurn=-1;
        index=-1;
        hasWon=false;
    }



    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
