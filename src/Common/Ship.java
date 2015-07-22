package Common;

import java.util.ArrayList;

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
    private ArrayList<int[]> availhits;

    public Ship(int ssize, String facing, int x, int y)
    {
        size = ssize;
        direction = facing;
        xcoordinate = x;
        ycoordinate = y;
        hits = 0;
        availhits = new ArrayList<int[]>();
    }

    protected Boolean hitShip(int x, int y)
    {
        for(int[] i : availhits)
        {
            if(i[0] == x && i[1] == y)
            {
                return true;

            }
        }
        return false;
    }

    //TODO: method to check available hits. boolean is sunk

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
