package com.bug;
import javax.swing.*;

public class Main {


    public static void main(String[] args) {
//        Organism foo = new Organism(gridX, gridY);//creating just a test organism
//        foo.spawn();//spawn 1 organmism at random coordinates

        JFrame jf = new JFrame("world");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,700);

        jf.add(new Window());
        jf.setVisible(true);
        /*while (true) {
            lastPos = foo.getPos();
            foo.move(lastPos);
            try {Thread.sleep(1000);} catch (Exception ex){}
        }*/
    }
//
//    static void makeStringyGrid(int[] grid) {
//        for (int i = 1; i < grid.length; i++) {
//            if (grid[i] == 1){
//                stringyGrid = stringyGrid.concat("\u03DA");
//            } else {
//                stringyGrid = stringyGrid.concat("\u2610");
//            }
//            if (i % gridX == 0){
//                stringyGrid = stringyGrid.concat("\n");
//            }
//        }
//    }
}


