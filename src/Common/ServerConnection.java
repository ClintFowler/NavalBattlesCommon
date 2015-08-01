package Common;

import game_server.message.Message;

import java.io.*;
import java.net.Socket;

/**
 * Joint class by Battleship group on 7/16/2015.
 */
public class ServerConnection implements Runnable
{
    private String userid;
    private GameFramework gameFramework;
    private ObjectOutputStream out;
    private Socket connection;

    public ServerConnection(GameFramework framework, String user)
    {
        gameFramework = framework;
        userid = user;
    }


    @Override
    public void run()
    {
        try
        {
            connection = new Socket("localhost",8989);
            out = new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
            out.writeUTF(userid);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));
            gameFramework.serverConnected();
            Message incomingMessage;
            while(!connection.isClosed() && connection.isConnected())
            {
                try
                {
                    incomingMessage = (Message) in.readObject();
                    gameFramework.messageHandler(incomingMessage);
                }catch (NullPointerException e)
                {
                    System.out.println("NULL ERROR");
//                    TODO: NullPointerException
                }catch (ClassNotFoundException e)
                {
                    System.out.println("Class Not Found ERROR");
//                    TODO: ClassNotFoundException
                }
            }

        }catch (IOException e)
        {
            gameFramework.failedConnection();
        }

    }

    protected void sendMessage(Message message)
    {
        try
        {
            out.writeObject(message);
            out.flush();
        }catch (IOException e)
        {
//            TODO: Failed to write OUTPUT
        }
    }
}
