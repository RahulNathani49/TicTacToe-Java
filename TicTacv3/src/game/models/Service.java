package game.models;

import java.util.ArrayList;

public interface Service {
    void defineWinningCases(int grid);

    void displayWinningCases();
    ArrayList<ArrayList<Integer>> getWinningCases();
    void storePlayerTurnIndex(int indexOfButton, int player);

    void displayPlayerXArray();

    void displayPlayerOArray();


    ArrayList<Integer> getPlayerO();


    ArrayList<Integer> getPlayerX();


    void setPlayerO(int index);

    void setPlayerX(int index);

    boolean hasWon(int player);

    int getTurnNumber();


    void resetData();

}
