package NavalBattles;

import Common.GameFramework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Clinton on 7/18/2015.
 */
public class StartWindow extends JFrame
{
    public StartWindow()
    {
        super("Navel Battles Launcher");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());
        constructwindow();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void constructwindow()
    {
        Dimension buttonsize = new Dimension(100,30);
        this.setContentPane(new JLabel(new ImageIcon("Resources/Images/Battleship6x4.png")));
        JPanel background = new JPanel(new GridLayout(3,1));
        background.setPreferredSize(new Dimension(100,100));

        final JButton start = new JButton("START GAME");
        start.setPreferredSize(buttonsize);
        start.setForeground(Color.WHITE);
        start.setVisible(true);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JButton exit = new JButton("EXIT");
        exit.setPreferredSize(buttonsize);
        exit.setForeground(Color.WHITE);
        exit.setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.getRuntime().exit(0);
            }
        });

        background.add(start);
        background.add((new JLabel())); //Spacer for grid layout
        background.add(exit);
        this.add(background, BorderLayout.CENTER);
        background.updateUI();
    }

    private void startGame()
    {
        this.dispose();
        new GameFramework();
    }
}
