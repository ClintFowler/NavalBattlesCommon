package Common;

/**
 * Created by Clinton on 7/16/2015.
 */
public class Ship
{
    private int size;
    private String direction;
    private int xcoordinate;
    private int ycoordinate;
    private int hits;

    public Ship(int ssize, String facing, int x, int y)
    {
        size = ssize;
        direction = facing;
        xcoordinate = x;
        ycoordinate = y;
        hits = 0;
    }

    //Accessor Methods
    protected int getXcoordinate()
    {
        int x = xcoordinate;
        return x;
    }

    protected int getYcoordinate()
    {
        int y = ycoordinate;
        return y;
    }

    protected int getSize()
    {
        int s = size;
        return s;
    }

    protected String getDirection()
    {
        String d = direction;
        return d;
    }

    protected void addHit()
    {
        if(hits < size)
        {
            hits++;
        }
    }

    protected boolean hasBeenSunk()
    {
        if (hits == size)
        {
            return true;
        }
        return false;
    }
}
