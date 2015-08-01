package Common;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Joint class by Battleship group on 7/16/2015.
 */

public class Gameboard extends JPanel
{
    private int cellWidth;
    private int cellHeight;

    public Gameboard()
    {
        super();
        this.setLayout(new GridLayout(10, 10, 0, 0));
        this.setBorder((new CompoundBorder(new EtchedBorder(), new LineBorder(Color.black))));
        this.setOpaque(false);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        cellWidth = width/10;
        cellHeight = height/10;

        for(int row = 1; row < 10; row++)
        {
            int vPos = cellHeight*row;
            g.drawLine(0,vPos,width,vPos);
        }

        for(int col = 1; col < 10; col++)
        {
            int hPos = cellWidth*col;
            g.drawLine(hPos,0,hPos,height);
        }
    }

    protected int getCellWidth()
    {
        return cellWidth;
    }

    protected int getCellHeight()
    {
        return cellHeight;
    }

//    public static void main(String[] args)
//    {
//        JPanel panel = new Gameboard();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        panel.setPreferredSize(new Dimension(300, 300));
//        frame.setSize(300, 300);
//        panel.setBorder((new CompoundBorder(new EtchedBorder(), new LineBorder(Color.black))));
//        frame.add(panel);
//        frame.setVisible(true);
//
//    }
}