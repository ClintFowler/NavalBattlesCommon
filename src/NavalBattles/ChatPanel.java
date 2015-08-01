package NavalBattles;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Clinton on 7/29/2015.
 */
public class ChatPanel extends JPanel
{
    private JTextArea chatwindow;

    public ChatPanel(JPanel chatinputpanel)
    {
        super();
        this.setPreferredSize(new Dimension(300, 1000));
        this.setLayout(new BorderLayout());

        chatwindow = new JTextArea();
        chatwindow.setLineWrap(true);
        chatwindow.setWrapStyleWord(true);
        chatwindow.setEditable(false);

        JScrollPane chatScroll = new JScrollPane(chatwindow);
        chatScroll.setPreferredSize(new Dimension(300, 900));
        chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatScroll.setAutoscrolls(true);

        this.add(chatScroll, BorderLayout.CENTER);
        this.add(chatinputpanel,BorderLayout.SOUTH);

    }

    protected void addChat(String chat)
    {
        chatwindow.append(chat + "\n\n");
        chatwindow.setCaretPosition(chatwindow.getText().length());
    }

}
