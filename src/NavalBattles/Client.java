package NavalBattles;

import Common.GameFramework;
import Common.Gameboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JLabel timerdisplay;
    private JTextArea systemMessages;
    private JButton startGButton;
    private JButton joinGButton;
    private JButton lockGBoard;
    private JButton attackButton;
    private Gameboard cDisplayBoard;

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
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        timerdisplay = new JLabel();
        timerdisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerdisplay.setForeground(Color.WHITE);
        timerdisplay.setFont(new Font("Serif", Font.BOLD, 25));
        timerdisplay.setFocusable(false);
        timerdisplay.setText("GAME TIMER HERE");
        timerdisplay.setPreferredSize(new Dimension(200, 30));
        timerdisplay.setOpaque(true);
        timerdisplay.setBackground(Color.black);

        cDisplayBoard = new Gameboard();
        cDisplayBoard.setPreferredSize(new Dimension(200, 200));
        cDisplayBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getCellLocation(e.getX(),e.getY());
            }
        });

        JPanel boardDisplayPanel = new JPanel();
        boardDisplayPanel.setOpaque(false);
        boardDisplayPanel.setLayout(new BoxLayout(boardDisplayPanel, BoxLayout.LINE_AXIS));
        boardDisplayPanel.add(Box.createRigidArea(new Dimension(150, 200)));
        boardDisplayPanel.add(cDisplayBoard);
        boardDisplayPanel.add(Box.createRigidArea(new Dimension(150,200)));

        systemMessages = new JTextArea();
        systemMessages.setEditable(false);
        systemMessages.setLineWrap(true);
        systemMessages.setWrapStyleWord(true);
        systemMessages.setForeground(Color.YELLOW);
        systemMessages.setBackground(Color.black);
        systemMessages.setFont(new Font("Times Roman", Font.BOLD, 15));

        JScrollPane systemScroll = new JScrollPane(systemMessages);
        systemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        systemScroll.setBackground(Color.black);

        JPanel systemdisplaypanel = new JPanel();
        systemdisplaypanel.setOpaque(false);
        systemdisplaypanel.setPreferredSize(new Dimension(300, 75));
        systemdisplaypanel.setLayout(new BoxLayout(systemdisplaypanel, BoxLayout.LINE_AXIS));
        systemdisplaypanel.add(Box.createRigidArea(new Dimension(400, 75)));
        systemdisplaypanel.add(systemScroll);
        systemdisplaypanel.add(Box.createRigidArea(new Dimension(100,75)));
        systemdisplaypanel.add(createButtonPanel());

        center.add(Box.createRigidArea(new Dimension(300,25)));
        center.add(timerdisplay);
        center.add(Box.createRigidArea(new Dimension(300, 75)));
        center.add(boardDisplayPanel);
        center.add(Box.createRigidArea(new Dimension(300,150)));
        center.add(systemdisplaypanel);
        center.add(Box.createRigidArea(new Dimension(300,50)));

        this.add(center, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 75));
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JPanel leftbuttons = new JPanel();
        leftbuttons.setPreferredSize(new Dimension(200, 75));
        leftbuttons.setLayout(new BoxLayout(leftbuttons, BoxLayout.PAGE_AXIS));
        leftbuttons.setOpaque(false);

        JPanel rightbuttons = new JPanel();
        rightbuttons.setPreferredSize(new Dimension(200, 75));
        rightbuttons.setLayout(new BoxLayout(rightbuttons, BoxLayout.PAGE_AXIS));
        rightbuttons.setOpaque(false);

        Dimension buttonsize = new Dimension(75,35);
        startGButton = new JButton("Start Game");
        joinGButton = new JButton("Join Game");
        lockGBoard = new JButton("Lock Board");
        attackButton = new JButton("Attack");

        startGButton.setPreferredSize(buttonsize);
        joinGButton.setPreferredSize(buttonsize);
        lockGBoard.setPreferredSize(buttonsize);
        attackButton.setPreferredSize(buttonsize);

        startGButton.setAlignmentX(CENTER_ALIGNMENT);
        joinGButton.setAlignmentX(CENTER_ALIGNMENT);
        lockGBoard.setAlignmentX(CENTER_ALIGNMENT);
        attackButton.setAlignmentX(CENTER_ALIGNMENT);

        leftbuttons.add(startGButton);
        leftbuttons.add(joinGButton);
        rightbuttons.add(attackButton);
        rightbuttons.add(lockGBoard);

        buttonPanel.add(leftbuttons);
        buttonPanel.add(rightbuttons);

        return buttonPanel;
    }

    private void setupWestPanel()
    {
        setupChat();
        JPanel west = new JPanel();
        west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));
        JPanel myBoard = new Gameboard();
        myBoard.setPreferredSize(new Dimension(300, 300));
        west.add(myBoard);
        chat.setOpaque(false);
        west.setOpaque(false);
        west.add(chat);
        this.add(west, BorderLayout.WEST);
    }

    private void setupChat()
    {
        JPanel chatinputpanel = new JPanel(new BorderLayout());

        chatinput = new JTextField();
        chatinput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && !chatinput.getText().equals("")) {
                    gameFramework.sendChatMessage(chatinput.getText());
                    chatinput.setText("");
                }
            }
        });

        JButton send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chatinput.getText().equals("")) {
                    gameFramework.sendChatMessage(chatinput.getText());
                }
                chatinput.setText("");
            }
        });

        chatinputpanel.add(send, BorderLayout.WEST);
        chatinputpanel.add(chatinput,BorderLayout.CENTER);
        chat = new ChatPanel(chatinputpanel);
    }

    private void getCellLocation(int x, int y)
    {
        int xcoord = 0;
        int ycoord = 0;
        xcoord = (int)Math.floor((x/cDisplayBoard.getCellWidth()));
        ycoord = (int)Math.floor((y/cDisplayBoard.getCellHeight()));
//        chat.addChat("X: " + x + " Y: " + y + "Width: " + cDisplayBoard.getCellWidth() + " Height: " + cDisplayBoard.getCellHeight() + " Coordinate: " + xcoord + "," + ycoord);
    }
    /**
     * This section is for defining methods to update the user display from
     * messages received from the server.
     */

    public void addIncomingChat(String chatmessage)
    {
        chat.addChat(chatmessage);
    }

    public void addSystemMessage(String sysmessage)
    {
        systemMessages.append(sysmessage +"\n\n");
        systemMessages.setCaretPosition(systemMessages.getText().length());
    }

    //test method
    public static void main(String[] args)
    {
        new GameFramework();
    }
}
