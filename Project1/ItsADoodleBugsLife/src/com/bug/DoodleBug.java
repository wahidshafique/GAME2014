package com.bug;

/**
 * Created by Wahid on 10/2/2016.
 */
public class DoodleBug extends Organism {
    int index;
    int hunger = 3;
    Boolean ate = false;
    Boolean alive = true;

    DoodleBug(int gridX, int gridY, int grid[]) {
        super(gridX, gridY, grid);
        setCreatureNum(1);
        super.breedingAgeInterval = 8;
    }

    void eat(int row, int col) {
        System.out.println("doodle eat");
        ate = true;
        hunger++;
        setElement(row, col);
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
            ate = false;
        }
    }

    @Override
    int[] move(int[] grid) {
        if (alive) {
            hunger--;
            if (hunger != 0) {
                eatSniff(get2DPositionX(super.currPosition), get2DPositionY(super.currPosition));
            } else {
                remElement(get2DPositionX(super.currPosition), get2DPositionY(super.currPosition));
                alive = false;
                System.out.println("DOODLE DEAD");
            }
            return (ate) ? super.grid : super.move(grid);
        } else {
            remElement(get2DPositionX(super.currPosition), get2DPositionY(super.currPosition));
            Window.doodleBugs.remove(this);
            return super.grid;
        }
    }

    @Override
    int[] breed(int row, int col) {
        System.out.println("doodle breed");
        Window.doodleBugs.add(Window.doodleBugs.size() - 1, new DoodleBug(gridX,gridY,grid));
        return super.breed(row, col);
    }
}
