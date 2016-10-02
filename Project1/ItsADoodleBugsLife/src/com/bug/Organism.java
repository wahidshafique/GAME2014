package com.bug;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Wahid on 9/29/2016.
 */
public class Organism {
    final int gridX;
    final int gridY;
    int currPosition;
    int total;//X and Y multiplied
    int grid[];

    Organism(int gridX, int gridY) {//supply constructor with organism grid params
        this.gridX = gridX;
        this.gridY = gridY;
    }
    //get the positions of the Organisms in 2d coordinates
    int get2DPositionX (int curr) {
            return curr % gridY;
    }
    int get2DPositionY (int curr) {
        return curr / gridY;
    }

    void setElement(int row, int col, int value) {
        int tempPos = row + (col * gridY);
        System.out.println("curr pos" + currPosition);
        if (tempPos < total && tempPos >= 0){//as long as the values do not exceed the grid
            currPosition = tempPos;
            grid[currPosition] = value;
        }
    }
    void spawn(){//create the entity in a random location on the grid
        total = gridX * gridY;
        grid = new int[total];//define the maximal len of array
        setElement(ThreadLocalRandom.current().nextInt(0, gridX + 1), ThreadLocalRandom.current().nextInt(0, gridY + 1), 1);
    }

    void move(int lastPos) {
        setElement (get2DPositionX(lastPos), get2DPositionY(lastPos), 0);//get rid of last position
        int dirX = ThreadLocalRandom.current().nextInt(-1, 2);
        int dirY = ThreadLocalRandom.current().nextInt(-1, 2);
        setElement (get2DPositionX(currPosition) + dirX, get2DPositionY(currPosition) + dirY, 1);

    }

    int[] getGrid(){//getter
        return grid;
    }
    int getPos(){
        return currPosition;
    }


    /*
    an Organism is the parent class of Ant and Doodlebug
    It has a STRICT boundary limit, basic movement patterns and behaviour

       This class holds position values for each generated critter,
       so that the child critter knows where its siblings are,

       if a critter is a 2x2, then everyone will know he is there, and of what type he is


     */
}
