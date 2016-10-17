package com.bug;

/**
 * Created by Wahid on 10/2/2016.
 */
public class DoodleBug extends Organism {
    int index;
    int hunger = 3;
    boolean ate = false;

    DoodleBug(int gridX, int gridY, int grid[]) {
        super(gridX, gridY, grid);
        setCreatureNum(1);
        breedingAgeInterval = 8;
    }

    void eat(int row, int col) {
        System.out.println("doodle eat");
        hunger++;

        for (int i = 0; i < Window.ants.size() ; i++) {
            if (Window.ants.get(i).getPos() == get1DPosition(row,col)){
                Window.ants.get(i).remElement(row, col);
                Window.ants.remove(i);
            }
        }
        setElement(row, col);
        ate = true;
    }

    void eatSniff(int row, int col) {
        tempPos = get1DPosition(row, col);
        if ((tempPos < total && tempPos > 0) && (row > 0 && row < gridX - 1) && (col < gridY - 1 && col > 0)) {//as long as the values do not exceed the grid
            if (grid[get1DPosition(row, col - 1)] == 2) {//check if top is full of ant
                eat(row, col - 1);
            } else if (grid[get1DPosition(row + 1, col)] == 2) {
                eat(row + 1, col);
            } else if (grid[get1DPosition(row, col + 1)] == 2) {
                eat(row, col + 1);
            } else if (grid[get1DPosition(row - 1, col)] == 2) {
                eat(row - 1, col);
            }
        } else {
            super.move(grid);
        }
    }

    @Override
    int[] move(int[] grid) {

        ate = false;
        hunger--;
        if (hunger != 0) {//if you are not starving
            age++;
            eatSniff(get2DPositionX(currPosition), get2DPositionY(currPosition));

        } else {//dead
            System.out.println("doodle dead");
            remElement(get2DPositionX(currPosition), get2DPositionY(currPosition));
            Window.doodleBugs.remove(this);
            return grid;
        }
        if(ate)
            return grid;
        else return super.move(grid);
    }

    @Override
    void breed(int row, int col) {
        System.out.println("doodle breed");
        Organism newDood = new DoodleBug(gridX, gridY, grid);
        newDood.setElement(row, col);
        Window.doodleBugs.add(Window.doodleBugs.size(), newDood);

    }
}
