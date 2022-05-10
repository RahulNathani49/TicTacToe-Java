package game.views;

import game.client.Client;
import game.controller.GameController;
import game.server.Server;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class Panel extends JPanel {
    private JButton[] boardBox;
    private JTextField gridInput;

    private JPanel welcomePanel;
    private JLabel gridTypeTitle;
    JLabel welcomePanelTitle;
    JLabel selectOption;
    JButton playerVsPlayer;
    JButton playerVsHost;
    JButton hostVsHost;
    JButton playerVsAI;

    JPanel playerVsPlayerView;
    JPanel playerVsHostView;
    JPanel hostVsHostView;
    JPanel hostVsAIView;
    private JLabel gameResult;
    private int gridSize;

    private  int gameMode;

    private int player1Turn;

    private JLabel title;
    JPanel gamePad;
    JButton createButton = new JButton("Create Grid");


    public Panel() {

        super();
        //initialisation
         playerVsPlayer=new JButton("Player vs Player");
         playerVsPlayer.setFont(new Font("sans serif", Font.BOLD, 13));
         playerVsPlayer.setFocusPainted(false);
         playerVsPlayer.setBackground(new ColorUIResource(new Color(220,20,60)));

         playerVsHost=new JButton("Host Game");
        playerVsHost.setFont(new Font("sans serif", Font.BOLD, 13));
        playerVsHost.setFocusPainted(false);
        playerVsHost.setBackground(new ColorUIResource(new Color(220,20,60)));

        hostVsHost=new JButton("Join Game" );
        hostVsHost.setFont(new Font("sans serif", Font.BOLD, 13));
        hostVsHost.setFocusPainted(false);
        hostVsHost.setBackground(new ColorUIResource(new Color(220,20,60)));

         playerVsAI=new JButton("Player vs AI");
        playerVsAI.setFont(new Font("sans serif", Font.BOLD, 13));
        playerVsAI.setFocusPainted(false);
        playerVsAI.setBackground(new ColorUIResource(new Color(220,20,60)));
        createWelcomePanel();
    }

//OMKAR
    //Welcome Screen
     private void createWelcomePanel() {
        welcomePanel=new JPanel();
        welcomePanel.setSize(new Dimension(1000,1000));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

         welcomePanelTitle=new JLabel("Lets Play Tic Tac Toe !!!");
         welcomePanelTitle.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
         welcomePanelTitle.setFont(new Font("sans serif", Font.BOLD, 24));
         welcomePanelTitle.setOpaque(true);
         welcomePanelTitle.setForeground(Color.white);
         welcomePanelTitle.setBackground(new ColorUIResource(new Color(14,52,91)));


         selectOption=new JLabel("Select option",SwingConstants.CENTER);
         selectOption.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         selectOption.setFont(new Font("sans serif", Font.BOLD, 18));
         selectOption.setOpaque(true);
         selectOption.setForeground(Color.white);
         selectOption.setBackground(new ColorUIResource(new Color(14,52,91)));
         selectOption.setHorizontalAlignment(SwingConstants.CENTER);

         welcomePanel.add(welcomePanelTitle);
         welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
         welcomePanel.add(selectOption);
         welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
         welcomePanel.add(playerVsPlayer);
         welcomePanel.add(Box.createRigidArea(new Dimension(0,10)));
         welcomePanel.add(playerVsHost);
         welcomePanel.add(Box.createRigidArea(new Dimension(0,10)));
         welcomePanel.add(hostVsHost);
         welcomePanel.add(Box.createRigidArea(new Dimension(0,10)));
         welcomePanel.add(playerVsAI);

         add(welcomePanel);
    }
    //it displays player vs player view

//    RAHUL
    public void createView(int gameMode) {
        this.gameMode = gameMode;
        if(gameMode==1)
        {
            welcomePanel.setVisible(false);
            playerVsPlayerView=new JPanel();
            displayInputGridValueView();
            playerVsPlayerView.setLayout(new BoxLayout(playerVsPlayerView, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        }
        if (gameMode == 2) {

            Panel contentPanel = new Panel();
            welcomePanel.setVisible(false);
            playerVsPlayerView=new JPanel();
            displayInputGridValueView();
            playerVsPlayerView.setLayout(new BoxLayout(playerVsPlayerView, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            GameController gameController = new GameController(contentPanel);
            contentPanel.add(welcomePanel);
            Window window = new game.utility.swing.Window("Tic Tac Toe on server",contentPanel);
            window.setVisible(true);
        }
        if(gameMode==4)
        {
            welcomePanel.setVisible(false);
            playerVsPlayerView=new JPanel();
            playerVsPlayerView.setSize(new Dimension(1000,1000));
            displayInputGridValueView();
            playerVsPlayerView.setLayout(new BoxLayout(playerVsPlayerView, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        }


        //input inputGridSizeView displays the screen where user inputs the size of the grid
    /*    JPanel inputGridSizeView =new JPanel();
                inputGridSizeView= GameGridSizeSelectionView();
        //for displaying game result
        gameResult=new JLabel();
        JPanel playerTurn = displayCurrentPlayerTurn();
        playerVsPlayerView.add(inputGridSizeView);
        playerVsPlayerView.add(createGridButtons());
        playerVsPlayerView.add(playerTurn);
        playerVsPlayerView.add(gameResult);
        add(playerVsPlayerView);*/

    }

    private void displayInputGridValueView() {
        JPanel inputGridSizeView =new JPanel();
        inputGridSizeView= GameGridSizeSelectionView();
        //for displaying game result
        gameResult=new JLabel();
        JPanel playerTurn = new JPanel();
        playerTurn= displayCurrentPlayerTurn();
        playerVsPlayerView.add(inputGridSizeView);
        playerVsPlayerView.add(createGridButtons());
        playerVsPlayerView.add(playerTurn);
        playerVsPlayerView.add(gameResult);
        add(playerVsPlayerView);
    }

    public void createGridActionListener(ActionListener gridButtonListener) {
        createButton.addActionListener(gridButtonListener);
        createButton.setBackground(Color.green);
    }

    public void createPlayervsPlayerActionListener(ActionListener listener){
        playerVsPlayer.addActionListener(listener);
    }

    public void createPlayervsHostActionListener(ActionListener listener)
    {
        playerVsHost.addActionListener(listener);
    }

    public void createHostvsHostActionListener(ActionListener listener){
        hostVsHost.addActionListener(listener);
    }

    public void createPlayervsAIActionListener(ActionListener listener){
        playerVsAI.addActionListener(listener);
    }


    public void createGamePad(int grid) {
        gamePad = new JPanel();
        gamePad.setLayout(new GridLayout(grid, grid));
        gamePad.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        boardBox = new JButton[grid * grid];

        for (int i = 0; i < grid * grid; i++) {
            String id= String.valueOf(i);

            boardBox[i] = new JButton(id);
            boardBox[i].setPreferredSize(new Dimension(100, 100));
            boardBox[i].setFont(new Font("MV Boli",Font.BOLD,20));

            gamePad.add(boardBox[i]);
        }


        playerVsPlayerView.add(gamePad);
    }
    private JPanel GameGridSizeSelectionView() {
        JPanel topTitle = new JPanel();
        topTitle.setMaximumSize(new Dimension(200, 200));
      gridTypeTitle = new JLabel("Select Grid Type");
        gridTypeTitle.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        gridTypeTitle.setFont(new Font("sans serif", Font.BOLD, 24));
        gridTypeTitle.setOpaque(true);
        gridTypeTitle.setForeground(Color.white);
        gridTypeTitle.setBackground(new ColorUIResource(new Color(14,52,91)));

        topTitle.add(gridTypeTitle);
        return topTitle;
    }

    private JPanel createGridButtons() {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(200, 3000));
        gridInput = new JTextField(10);
        panel.add(gridInput);
        panel.add(createButton);
        return panel;
    }







    public void buttonClickedListener(ActionListener onbuttonClicked) {
        for(int i=0;i<gridSize;i++)
        {
            boardBox[i].addActionListener(onbuttonClicked);
        }
    }


    //hide grid incase of win /loose or draw condition
    public void hideGamePad(){
        gamePad.setVisible(false);
    }
    //setter methods
    public void setGameResultText(String text) {
        gameResult.setText(text);
    }
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
    public void setPlayer1Turn(int player1Turn) {
        this.player1Turn = player1Turn;
    }
    public void setTitle(String title) {
        this.title.setText(title);
    }
    //getter methods

    public int getGridSize() {
        //returns max gridSize
        return gridSize;
    }

    public int getPlayer1Turn() {
        return player1Turn;
    }



    //calculated getters
    public int getGridInput() {
        return Integer.parseInt(gridInput.getText());
    }
    private JPanel displayCurrentPlayerTurn() {
        JPanel topTitle = new JPanel();
        topTitle.setMaximumSize(new Dimension(200, 200));
        int random = (int) Math.round(Math.random());
        title = new JLabel();
        title.add(Box.createRigidArea(new Dimension(0,20)));


        if (random == 0) {
            title.setText("Player Turn : O");
            setPlayer1Turn(0);

        } else {
            title.setText("Player Turn : X");
            setPlayer1Turn(1);
            if (gameMode == 4){
                title.setText("Player Turn : O");
                setPlayer1Turn(0);
            }
        }

        topTitle.setBackground(new ColorUIResource(new Color(14,52,91)));
        title.setForeground(Color.white);
        title.setFont(new Font("sans serif", Font.BOLD, 20));


        topTitle.add(title);
        return topTitle;
    }

    public void unHideField() {
        gameResult.setVisible(true);
    }
    public void hideField(){
        gameResult.setVisible(false);
    }

    public JTextField getTextField() {
        return gridInput;
    }

    public JButton getCreateGridButton() {
        return createButton;
    }

    public JLabel gameGridSizeSelectionView() {
        return gridTypeTitle;
    }
    public JButton[] getBoardBox (){
       return boardBox;
    }

    public void displayPopup(String s) {
        if (s==""){
            JOptionPane.showMessageDialog(null, "Match Draw");
        }
        else{
            JOptionPane.showMessageDialog(null, "Player "+s +" Wins");
        }
    }

    public int getGameMode() {
         return this.gameMode;
    }
}
