package com.bug;

/**
 * Created by Wahid on 10/2/2016.
 */
public class Ant extends Organism{
    Ant(int gridX, int gridY, int grid[]) {
        super(gridX, gridY, grid);
        setCreatureNum(2);
        breedingAgeInterval = 3;
    }

    @Override
    int[] move(int[] grid) {
        age++;
        super.move(grid);
        if (age % breedingAgeInterval == 0) {
            breedSniff(get2DPositionX(currPosition), get2DPositionY(currPosition));
        }
        return grid;
    }

    @Override
    void breed(int row, int col) {
        Organism newAnt = new Ant(gridX, gridY, grid);
        newAnt.setElement(row, col);
        Window.ants.add(Window.ants.size(), newAnt);
    }

    public int getPos(){
        return currPosition;
    }

}
