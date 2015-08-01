package NavalBattles;

import Common.GameFramework;
import javafx.scene.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        this.setSize(600,400);
        constructwindow();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void constructwindow()
    {
        this.setContentPane(new JLabel(new ImageIcon("Resources/Images/Battleship6x4.png")));
        this.setLayout(null);

        JLabel title = new JLabel("NAVAL BATTLES");
        title.setSize(400, 40);
        title.setForeground(Color.RED);
        title.setFont(new Font("Serif", Font.BOLD, 45));

        final JLabel start = new JLabel("START");
        start.setSize(175, 50);
        start.setForeground(Color.BLUE);
        start.setFont(new Font("Serif", Font.BOLD, 40));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                start.setFont(new Font("Serif", Font.BOLD, 45));
                start.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                start.setFont(new Font("Serif", Font.BOLD, 40));
                start.setForeground(Color.BLUE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                startGame();
            }
        });

        final JLabel exit = new JLabel("EXIT");
        exit.setSize(150, 50);
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Serif", Font.BOLD, 40));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                exit.setFont(new Font("Serif", Font.BOLD, 45));
                exit.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                exit.setFont(new Font("Serif", Font.BOLD, 40));
                exit.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Runtime.getRuntime().exit(0);
            }
        });

        title.setLocation(115,40);
        start.setLocation(240, 130);
        exit.setLocation(260, 210);
        this.add(start);
        this.add(exit);
        this.add(title);
    }

    private void startGame()
    {
        this.dispose();
        new GameFramework();
    }
}
