package Common;

import java.io.Serializable;

/**
 * Joint class by Battleship group on 7/16/2015.
 */
public class GameboardArray implements Serializable {
    private int[][] gameBoard;
    private int xsize = 10;
    private int ysize = 10;

    //class constants
    public static final int HIT = 1;
    public static final int MISS = 0;




    public GameboardArray()
    {
        gameBoard = new int[xsize][ysize];
        //initializeBoard(); //are we going to initialize to a set value or work with null?
    }

    public void addShip(int xcoord, int ycoord, Ship s)
    {
    	if(s.getDirection().equals(Ship.HORIZONTAL))
    	{
    		addShipHorizontal(s.getSize(), xcoord, ycoord);
    	}
    	else
    	{
    		addShipVertical(s.getSize(), xcoord, ycoord);
    	}
    }
    
    public void addShipHorizontal(int size, int xcoord, int ycoord)
    {
    	for(int i = 0; i < size; i++)
		{
    		if(checkOutOfBound(xcoord, ycoord))
    		{
    			gameBoard[xcoord][ycoord] = size;
    			ycoord++;
    		}
		}
    }
    
    public void addShipVertical(int size, int xcoord, int ycoord)
    {
    	for(int i = 0; i < size; i++)
		{
    		if(checkOutOfBound(xcoord, ycoord))
    		{
    			gameBoard[xcoord][ycoord] = size;
    			xcoord++;
    		}
		}
    }
    
    public boolean checkOutOfBound(int xcoord, int ycoord)
    {
    	if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord < xsize )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    

	protected void addHit(int x, int y)
	{
		gameBoard[x][y] = HIT;
	}

	protected void addMiss(int x, int y)
	{
		gameBoard[x][y] = MISS;
	}

}

