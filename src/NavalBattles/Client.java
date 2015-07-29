package NavalBattles;

import javax.swing.*;

/**
 * Created by Clinton on 7/19/2015.
 */
public class Client extends JFrame
{
    private int xScreenSize;
    private int yScreenSize;

    public Client()
    {
        super("Naval Battles");
        xScreenSize = getToolkit().getScreenSize().width;
        yScreenSize = getToolkit().getScreenSize().height;
        this.setSize(xScreenSize,yScreenSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(new JLabel(new ImageIcon("Resources/Images/")));
    }
}
