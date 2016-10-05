package com.bug;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/**
 * Created by Wahid on 9/29/2016.
 */
public abstract class Organism {
    final int gridX;
    final int gridY;
    int currPosition = 0;
    int lastPosition = 0;
    int tempPos;
    int total;//X and Y multiplied
    int grid[];

    private int creatureNum;
    private int age = 0;
    int breedingAgeInterval = 100;

    Organism(int gridX, int gridY, int grid[]) {//supply constructor with organism grid params
        this.gridX = gridX;
        this.gridY = gridY;
        this.grid = grid;
        total = gridX * gridY;
    }
    //get the positions of the Organisms in 2d coordinates
    int get2DPositionX (int curr) {
            return curr % gridY;
    }
    int get2DPositionY (int curr) {
        return curr / gridY;
    }
    int get1DPosition (int row, int col) {
        return row + (col * gridY);
    }

    void setElement(int row, int col) {
            tempPos = get1DPosition(row,col);
            currPosition = tempPos;
            grid[currPosition] = creatureNum;
            lastPosition = currPosition;

        }

     void remElement(int row, int col) {
         int pos = get1DPosition(row,col);
         grid[pos] = 0;
     }

     int[] breed (int row, int col) {//
         grid[get1DPosition(row, col)] = creatureNum;
         System.out.println("SPAWNED FIXED");
         return grid;
     }

     void breedSniff(int row, int col) {
         tempPos = get1DPosition(row, col);
         if ((tempPos < total && tempPos > 0) && (row > 0 && row < gridX - 1) && (col < gridY - 1 && col > 0)) {//as long as the values do not exceed the grid
             if (grid[get1DPosition(row, col - 1)] == 0) {//check if top is clear
                 breed (row, col - 1);
             } else if (grid[get1DPosition(row + 1, col)] == 0) {//check right
                 breed (row + 1, col);
             } else if (grid[get1DPosition(row, col + 1)] == 0) {//check bottom
                 breed (row, col + 1);
             } else if (grid[get1DPosition(row - 1, col)] == 0) {//check left
                 breed (row - 1, col);
             }
         }
     }

    void sniff (int row, int col) {
        tempPos = get1DPosition(row,col);
        if ((tempPos < total && tempPos > 0) && (row > 0 && row < gridX - 1) && (col < gridY - 1 && col > 0)) {//as long as the values do not exceed the grid
            if (grid[get1DPosition(row, col)] == 0) {
                setElement(row, col);

            } else if (grid[get1DPosition(row, col - 1)] == 0) {//check if top is clear
                setElement(row, col - 1);

            } else if (grid[get1DPosition(row + 1, col)] == 0) {//check right
                setElement(row + 1, col);

            } else if (grid[get1DPosition(row, col + 1)] == 0) {//check bottom
                setElement(row, col + 1);

            } else if (grid[get1DPosition(row - 1, col)] == 0) {//check left
                setElement(row - 1, col);
            }
        } else {
            lastPosition = currPosition;
            setElement(get2DPositionX( lastPosition), get2DPositionY(lastPosition));
            System.out.println("Stay");}
}

    int[] spawn(){//create the entity in a random location on the grid
        sniff(ThreadLocalRandom.current().nextInt(0, gridX + 1), ThreadLocalRandom.current().nextInt(0, gridY + 1));
        System.out.println("SPAWNED RANDOM");
        return grid;
    }

    int[] move(int[] grid) {//basically random, the basis for any simple organism
        //follows chess like pattern to sometimes squeeze over obstacles to avoid cornering and behave sort of like
        //real bugs
        age++;
            remElement(get2DPositionX(lastPosition), get2DPositionY(lastPosition));//rm last pos
            int dirX = setRandom();
            int dirY = setRandom();

            sniff(get2DPositionX(currPosition) + dirX, get2DPositionY(currPosition) + dirY);
            if (age % breedingAgeInterval == 0) {
                breedSniff(get2DPositionX(currPosition), get2DPositionY(currPosition));
            }
            return grid;
        }

    int setRandom(){
        int val = ThreadLocalRandom.current().nextInt(-1, 2);
        return val != 0 ? val : setRandom();
}
    void setCreatureNum(int x) {
        if (x == 1 || x == 2) {
            this.creatureNum = x;
        } else {
            throw new IllegalArgumentException("Setting the grid number for creature can only be 1 or 2");
        }
    }

}
