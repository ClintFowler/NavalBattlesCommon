package NavalBattles;

import Common.GameFramework;
import Common.Gameboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Clinton on 7/19/2015.
 */
public class Client extends JFrame
{
    private int xScreenSize;
    private int yScreenSize;
    private BufferedImage image;
    private GameFramework gameFramework;
    private ChatPanel chat;
    private JTextField chatinput;
    private JList who;
    private JPanel displaybox;

    public Client(GameFramework framework)
    {
        super("Naval Battles");
        gameFramework = framework;
        xScreenSize = getToolkit().getScreenSize().width;
        yScreenSize = getToolkit().getScreenSize().height;
        this.setSize(xScreenSize, yScreenSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            image = ImageIO.read(new File("Resources/Images/Ocean.png"));
            JPanel background = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0,getWidth(),getHeight(), null);
                    repaint();
                }
            };
            this.setContentPane(background);
        }catch (IOException e)
        {
            //don't care about this error currently. this is for the background with makes no difference.
        }

        this.getContentPane().setLayout(new BorderLayout());

        setupWestPanel();
        setupEastPanel();
        setupCenterPanel();

        this.setVisible(true);
        this.requestFocus();
    }

    private void setupEastPanel()
    {
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
        east.setOpaque(false);
        displaybox = new JPanel();
        displaybox.setPreferredSize(new Dimension(300, 300));
        displaybox.setOpaque(false);
        who = new JList();
        JScrollPane userlist = new JScrollPane(who);
        userlist.setPreferredSize(new Dimension(300, 900));
        userlist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        east.add(displaybox);
        east.add(userlist);
        this.add(east, BorderLayout.EAST);
    }

    private void setupCenterPanel()
    {
        JPanel center = new JPanel();
        center.setOpaque(false);

        this.add(center, BorderLayout.CENTER);
    }

    private void setupWestPanel()
    {
        setupChat();
        JPanel west = new JPanel();
        west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));
        JPanel myBoard = new Gameboard();
        myBoard.setPreferredSize(new Dimension(300, 300));
        myBoard.setBorder((new CompoundBorder(new EtchedBorder(), new LineBorder(Color.black))));
        west.add(myBoard);
        myBoard.setOpaque(false);
        chat.setOpaque(false);
        west.setOpaque(false);
        west.add(chat);
        this.add(west, BorderLayout.WEST);
    }

    private void setupChat()
    {
        JPanel chatinputpanel = new JPanel(new BorderLayout());

        chatinput = new JTextField();
        chatinput.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && !chatinput.getText().equals(""))
                {
                    gameFramework.sendChatMessage(chatinput.getText());
                    chatinput.setText("");
                }
            }
        });

        JButton send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!chatinput.getText().equals(""))
                {
                    gameFramework.sendChatMessage(chatinput.getText());
                }
                chatinput.setText("");
            }
        });

        chatinputpanel.add(send, BorderLayout.WEST);
        chatinputpanel.add(chatinput,BorderLayout.CENTER);
        chat = new ChatPanel(chatinputpanel);
    }

    public void addIncomingChat(String chatmessage)
    {
        chat.addChat(chatmessage);
    }

    //test method
    public static void main(String[] args)
    {
        new GameFramework();
    }
}
