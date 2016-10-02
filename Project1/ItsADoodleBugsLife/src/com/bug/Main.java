package com.bug;

public class Main {
    int gridX = 20;
    int gridY = 20;
    int lastPos = 0;
    int grid[];

    public static void main(String[] args) {

        Main main = new Main();
        Organism foo = new Organism(main.gridX, main.gridY);
        foo.spawn();

        while (true) {
            main.lastPos = foo.getPos();
            foo.move(main.lastPos);
            main.grid = foo.getGrid();
            main.printGrid(main.grid);
        }
    }

    void printGrid(int[] grid) {
        int i = 0;
        for (int item : grid) {
            i++;
            if (item != 1)
                System.out.print("\u2610");
            else System.out.print("\u03DA");
            if (i % gridX == 0) {
                System.out.println();
            }
        }
    }
}


