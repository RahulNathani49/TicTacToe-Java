package game.models;

import java.util.ArrayList;

public class ServiceImpl implements Service {
    private ArrayList<ArrayList<Integer>> winningCases;
    private ArrayList<Integer> playerX;
    private ArrayList<Integer> playerO;

    private int turnNumber;

    public ServiceImpl() {
        winningCases = new ArrayList<>();
        playerX = new ArrayList<Integer>();
        playerO = new ArrayList<Integer>();
        turnNumber = 0;
    }

    @Override
    public void defineWinningCases(int grid) {
        int maxGridValue = grid * grid;
        ArrayList<Integer> horrizontalCases;
        ArrayList<Integer> verticalCases;
        ArrayList<Integer> leftDiagonalCase = new ArrayList<>();
        ArrayList<Integer> rightDiagonalCase = new ArrayList<>();


        for (int i = 0; i < maxGridValue; i++) {

            int previous = i;
            horrizontalCases = new ArrayList<>();
            verticalCases = new ArrayList<>();


            for (int j = 0; j < grid; j++) {
                //vertical
                if (i % grid == 0) {
                    verticalCases.add(i + j);
                }
                //horrizzontal
                if (i < grid) {
                    if (j == 0) {
                        horrizontalCases.add(i);
                    } else {
                        int next = previous + grid;
                        horrizontalCases.add(next);
                        previous = next;
                    }
                }

            }
            if (!horrizontalCases.isEmpty()) {
                winningCases.add(horrizontalCases);
            }
            if (!verticalCases.isEmpty()) {
                winningCases.add(verticalCases);
            }
            //left diagonal
            if (i % (grid + 1) == 0) {
                leftDiagonalCase.add(i);
            }
            //right diagonal
            if (i % (grid - 1) == 0 && i != 0 && i != maxGridValue - 1) {
                rightDiagonalCase.add(i);
            }

        }
        winningCases.add(leftDiagonalCase);
        winningCases.add(rightDiagonalCase);
    }

    @Override
    public void displayWinningCases() {
        for (ArrayList list : winningCases
        ) {
            System.out.println(list);

        }
    }

    @Override
    public void storePlayerTurnIndex(int indexOfButton, int player) {
        if (player == 0) {
            playerO.add(indexOfButton);
        }
        if (player == 1) {
            playerX.add(indexOfButton);
        }
        turnNumber++;
    }

    @Override
    public void displayPlayerXArray() {
        for (Integer x : playerX
        ) {
            System.out.println("X=" + x);

        }
    }

    @Override
    public void displayPlayerOArray() {
        for (Integer o : playerO
        ) {
            System.out.println("O=" + o);

        }
    }

    @Override
    public ArrayList<Integer> getPlayerO() {
        return playerO;
    }

    @Override
    public ArrayList<Integer> getPlayerX() {
        return playerX;
    }

    @Override
    public void setPlayerX(int index) {
        this.playerX.add(index);
    }

    @Override
    public void setPlayerO(int index) {
        this.playerO.add(index);
    }

    @Override
    public boolean hasWon(int player) {
        if (player == 0) {
            for (ArrayList list : winningCases) {

                if (playerO.containsAll(list)) {
                    return true;
                }
            }
        }
        if (player == 1) {
            for (ArrayList list : winningCases) {
                if (playerX.containsAll(list)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public ArrayList<ArrayList<Integer>> getWinningCases() {
        return winningCases;
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public void resetData() {
        turnNumber=0;
        winningCases.clear();
        playerO.clear();
        playerX.clear();
    }


}
