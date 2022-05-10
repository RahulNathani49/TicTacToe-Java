package game.utility.swing;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    public Window(String title, Container contentPane)
    {
        this(title, contentPane, true);
    }
    
    public Window(String title, Container contentPane, boolean resizable)
    {
        super(title);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        
        pack();
        setMinimumSize(getSize());
        setResizable(resizable);
    }
}
