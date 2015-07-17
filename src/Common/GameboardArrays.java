package Common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameboardArrays implements Serializable {
    private int[][] shipArray;
    private int[][] publicboard;
    private int xsize = 10;
    private int ysize = 10;
    public static final int HIT = 1;
    public static final int MISS = 0;




    public GameboardArrays()
    {
        shipArray = new int[xsize][ysize];
        publicboard = new int[xsize][ysize];
    }

    public GameboardArrays(ArrayList<Ship> addships)
    {
        //TODO: constructor
    }


    //Accessor Methods

    public int[][] getPublicboard()
    {
        int[][] viewableboard = publicboard;

        return viewableboard;
    }

    public void addGuess(int x, int y)
    {
        //TODO: addGuess
    }


}
