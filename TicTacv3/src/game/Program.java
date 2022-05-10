package game;

import game.controller.GameController;
import game.server.Server;
import game.views.Panel;
import javax.swing.*;
import java.awt.*;


public class Program
{
   public static void main(String[] args) {
       SwingUtilities.invokeLater(Program::start);
       Server server = new Server(7777);
       server.start();
   }
    public static void start()
    {

        useSystemAndFeel();
        Panel contentPanel = new Panel();
        GameController gameController=new GameController(contentPanel);
        Window window = new game.utility.swing.Window("Tic Tac Toe",contentPanel);
        window.setVisible(true);
    }

    private static void useSystemAndFeel(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }
}
