package Common;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameFramework
{
    private ServerConnection server;
    private String userid = "Clint";
    //add your game client window here

    public GameFramework()
    {
        server = new ServerConnection(this, userid);
        //call your game client constructor
    }

    //Handle incoming messages
    protected void messageHandler()
    {
        //TODO: Message Logic once Message class is built by Trevor
    }

    /**
     * Code will need to change for your methods in your client window. This is the middle man
     * it will handle messages to the server and updating the client window
     *
     * i will program the message handling portion once trevor builds the class but you will need
     * to adapt it to your client as needed.
     */

}
