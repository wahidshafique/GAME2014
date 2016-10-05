package com.bug;

/**
 * Created by Wahid on 10/2/2016.
 */
public class Ant extends Organism{
    Ant(int gridX, int gridY, int grid[]) {
        super(gridX, gridY, grid);
        setCreatureNum(2);
        super.breedingAgeInterval = 3;
    }

    @Override
    int[] move(int[] grid) {
        return super.move(grid);
    }

    @Override
    int[] breed(int row, int col) {
        Window.ants.add(Window.ants.size() - 1, new Ant(gridX,gridY,grid));
        return super.breed(row, col);
    }


}
