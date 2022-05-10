package game.controller;

import game.client.Client;
import game.models.Service;
import game.models.ServiceImpl;
import game.views.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class GameController {
    private final Panel view;
    private int indexToSend;
    private Service service=new ServiceImpl();

    public GameController(Panel view){
        this.view=view;
        view.createGridActionListener(this::onCreateGrid);
        view.createPlayervsPlayerActionListener(this::onClickPlayervsPlayer);
        view.createPlayervsHostActionListener(this::onClickPlayervsHost);
        view.createHostvsHostActionListener(this::onClickHostvsHost);
        view.createPlayervsAIActionListener(this::onClickPlayervsAI);
       // server= new Server(7777);
    }

//    RAHUL
    private void onClickPlayervsPlayer(ActionEvent actionEvent) {

    view.createView(1);
    }

    //    IN DEVELOPMENT
    private void onClickPlayervsHost(ActionEvent actionEvent) {
        view.createView(2);

    }

//    IN DEVELOPMENT
    private void onClickHostvsHost(ActionEvent actionEvent) {
        view.createView(3);
    }

//    OMKAR
    private void onClickPlayervsAI(ActionEvent actionEvent) {
        view.createView(4);
    }

    public void onButtonClicked(ActionEvent e){



        if (view.getGameMode()==1){
            Object o=e.getSource();
            JButton but= (JButton) o;
            System.out.println("button clicked="+but.getText());
            int butNum = Integer.parseInt(but.getText());
            int indexOfButton= Integer.parseInt(but.getText());
            int player=view.getPlayer1Turn();
            //setting button properties on button click
            if(player==0){
                but.setText("O");
                view.setPlayer1Turn(1);
                view.setTitle("Player turn : X");
            }
            else if(player==1)
            {
                view.getBoardBox()[butNum].setForeground(Color.BLUE);
                but.setText("X");
                view.setPlayer1Turn(0);
                view.setTitle("Player turn : O");
            }
            but.setEnabled(false);
            //

            service.storePlayerTurnIndex(indexOfButton,player);
            boolean hasWon=false;
            hasWon= service.hasWon(player);
            if(hasWon==true)
            {
                view.unHideField();
                if(player==0){
                    view.getTextField().setVisible(true);
                    view.getCreateGridButton().setVisible(true);
                    view.gameGridSizeSelectionView().setVisible(true);
                    view.displayPopup("O");

                }
                if(player==1)
                {
                    view.displayPopup("X");
                    view.getTextField().setVisible(true);
                    view.getCreateGridButton().setVisible(true);
                    view.gameGridSizeSelectionView().setVisible(true);
                }
                view.hideGamePad();
            }

            if(service.getTurnNumber()==view.getGridSize() && hasWon==false)
            {
                view.unHideField();
                view.hideGamePad();
                view.displayPopup("");
                view.getTextField().setVisible(true);
                view.getCreateGridButton().setVisible(true);
                view.gameGridSizeSelectionView().setVisible(true);
            }
            service.displayPlayerXArray();
            service.displayPlayerOArray();
        }

        if (view.getGameMode()==4){
            Object o=e.getSource();
            JButton but= (JButton) o;
            System.out.println("button clicked="+but.getText());
            int butNum = Integer.parseInt(but.getText());
            int indexOfButton= Integer.parseInt(but.getText());
            int player= view.getPlayer1Turn();
            if (player==0){
                but.setText("O");
                view.setPlayer1Turn(1);
                view.setTitle("Computers Turn : X");
                but.setEnabled(false);
                service.storePlayerTurnIndex(indexOfButton,player);

                boolean hasWon=false;
                hasWon= service.hasWon(player);
                if(hasWon==true)
                {
                    view.unHideField();
                    if(player==0){
                        view.getTextField().setVisible(true);
                        view.getCreateGridButton().setVisible(true);
                        view.gameGridSizeSelectionView().setVisible(true);
                        view.displayPopup("O");
                        view.setPlayer1Turn(0);
                        view.setTitle("Player Turn : O");
                        service.displayPlayerOArray();
                    }
                    if(player==1)
                    {
                        view.setPlayer1Turn(0);
                        view.displayPopup("Computer");
                        view.getTextField().setVisible(true);
                        view.getCreateGridButton().setVisible(true);
                        view.gameGridSizeSelectionView().setVisible(true);
                    }
                    view.hideGamePad();

                }
                if(service.getTurnNumber()==view.getGridSize() && hasWon==false)
                {
                    view.setTitle("Player Turn : 0");
                    view.unHideField();
                    view.displayPopup("");
                    view.getTextField().setVisible(true);
                    view.getCreateGridButton().setVisible(true);
                    view.gameGridSizeSelectionView().setVisible(true);

                }
                service.displayPlayerXArray();
                service.displayPlayerOArray();
                for(int i=0;i<view.getBoardBox().length;i++){
                    if(view.getBoardBox()[i].getText()!="X" ||view.getBoardBox()[i].getText()!="O" ){
                        computersTurn();
                    }
                    else
                    {
                        view.getTextField().setVisible(true);
                        view.getCreateGridButton().setVisible(true);
                        view.gameGridSizeSelectionView().setVisible(true);
                        view.displayPopup("O");
                        view.setPlayer1Turn(0);

                        service.displayPlayerOArray();
                    }
                }

            }
        }


        if (view.getGameMode()==2){
            Object o=e.getSource();
            JButton but= (JButton) o;
            int index = Integer.parseInt(but.getText());
            Client client = new Client("localhost",7777);
            client.start(index);
        }
    }

//    Omkar
    private void computersTurn() {

        int player= view.getPlayer1Turn();
        if(player==1){
//               Computers Play
            Random randomNum = new Random();
            int computerIndex = 0 + randomNum.nextInt(view.getGridSize());
            while (view.getBoardBox()[computerIndex].getText()=="X"  || view.getBoardBox()[computerIndex].getText()=="O"){
                computerIndex = 0 + randomNum.nextInt(view.getGridSize());
            }
            System.out.println("rand : " + computerIndex);
            System.out.println(view.getGridSize());

                if (view.getBoardBox()[computerIndex].getText() != "X" || view.getBoardBox()[computerIndex].getText() != "O" ){
                    view.getBoardBox()[computerIndex].setForeground(Color.RED);
                    view.getBoardBox()[computerIndex].setText("X");
                    view.getBoardBox()[computerIndex].setEnabled(false);
                    service.storePlayerTurnIndex(computerIndex,player);
                    view.setPlayer1Turn(0);
                    view.setTitle("Your turn : O");

                    boolean hasWon=false;
                    hasWon= service.hasWon(player);
                    if(hasWon==true)
                    {
                        view.unHideField();
                        if(player==0){
                            view.getTextField().setVisible(true);
                            view.getCreateGridButton().setVisible(true);
                            view.gameGridSizeSelectionView().setVisible(true);
                            view.displayPopup("O");
                        }
                        if(player==1)
                        {
                            view.setPlayer1Turn(0);
                            view.displayPopup("Computer");
                            view.getTextField().setVisible(true);
                            view.getCreateGridButton().setVisible(true);
                            view.gameGridSizeSelectionView().setVisible(true);
                        }
                        view.hideGamePad();
                    }
                    if(service.getTurnNumber()==view.getGridSize() && hasWon==false)
                    {
                        view.unHideField();
                        view.hideGamePad();
                        view.displayPopup("");
                        view.getTextField().setVisible(true);
                        view.getCreateGridButton().setVisible(true);
                        view.gameGridSizeSelectionView().setVisible(true);
                    }
                    service.displayPlayerXArray();
                    service.displayPlayerOArray();
                }
                else
                {
                    view.setTitle("Player Turn : 0");
                    view.unHideField();
                    view.hideGamePad();
                    view.displayPopup("");
                    view.getTextField().setVisible(true);
                    view.getCreateGridButton().setVisible(true);
                    view.gameGridSizeSelectionView().setVisible(true);
                }
        }

    }


    public void onCreateGrid(ActionEvent e){
        //every time user enter new grid value reset the grid content
        resetGrid();
            int grid = view.getGridInput();
            view.setGridSize(grid*grid);
            view.createGamePad(grid);
            view.revalidate();
            view.validate();
            service.defineWinningCases(grid);
            //service.displayWinningCases();
             view.buttonClickedListener(this::onButtonClicked);
             view.getTextField().setVisible(false);
             view.getCreateGridButton().setVisible(false);
             view.gameGridSizeSelectionView().setVisible(false);
             for (int i=0;i<view.getBoardBox().length;i++){
                view.getBoardBox()[i].setBackground(Color.white);
                view.getBoardBox()[i].setForeground(Color.white);
             }

        }

    private void resetGrid() {
        service.resetData();
        view.hideField();
    }

    public int getIndexToSend() {
        return indexToSend;
    }
}
