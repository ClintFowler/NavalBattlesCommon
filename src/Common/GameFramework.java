package Common;

import NavalBattles.Client;
import game_server.game.GameMessage;
import game_server.game.message.StatusMessage;
import game_server.message.*;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameFramework
{

    // basic structure for game
    private ServerConnection server;
    private String userid = "Clint Rules!";
    private Client client;

    public GameFramework()
    {
        server = new ServerConnection(this, userid);
        client = new Client(this);
        //startServer();
    }

    protected void failedConnection()
    {
        client.addIncomingChat("***************************\nSYSTEM: FAILED TO CONNECT\n***************************");
    }

    protected void serverConnected()
    {
        client.addIncomingChat("***************************\nSYSTEM: CONNECTED TO SERVER\n***************************");
    }

    //Handle incoming messages
    protected void messageHandler(Message message)
    {
        if(message.getMessageType().equals(Message.Type.CHAT))
        {
            if(message.getUsername() == null)
            {
                client.addIncomingChat(userid + ": " + ((ChatMessage)message).getText());
            }
            else
            {
                client.addIncomingChat(message.getUsername() + ": " + ((ChatMessage)message).getText());
            }
            return;
        }
        if(message.getMessageType().equals(Message.Type.ACKNOWLEDGE) || message.getMessageType().equals(Message.Type.DENY))
        {
            message = (ResponseMessage) message;
            //TODO: Response Message
            return;
        }
        if(message.getMessageType().equals(Message.Type.TIMER))
        {
            message = (TimerMessage) message;
            //TODO: Timer Message
            return;
        }
        if(message.getMessageType().equals(Message.Type.START_GAME) || message.getMessageType().equals(Message.Type.END_GAME)
                || message.getMessageType().equals(Message.Type.OBSERVE_GAME) ||message.getMessageType().equals(Message.Type.JOIN_GAME))
        {
            message = (StatusMessage) message;
            //TODO: Status Message
            return;
        }
        if(message.getMessageType().equals(Message.Type.GAME))
        {
            message = (GameMessage) message;
            //TODO: Game Message
            return;
        }
    }

    public void sendChatMessage(String mes)
    {
        Message message = new ChatMessage(mes,userid);
        sendServerMessage(message);
    }

    private void sendServerMessage(Message message)
    {
        server.sendMessage(message);
    }

    //start a thread for server connection
    protected void startServer()
    {
        Thread thread = new Thread(server);
        thread.start();
    }

    /**
     * Code will need to change for your methods in your client window. This is the middle man
     * it will handle messages to the server and updating the client window
     *
     * i will program the message handling portion once trevor builds the class but you will need
     * to adapt it to your client as needed.
     */

}
