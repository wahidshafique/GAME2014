package com.bug;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wahid on 10/2/2016.
 */
public class Window extends JPanel {
    final int gridX = 20;
    final int gridY = 20;
    final int numDoodle;
    final int numAnts;
    final int timeStep;

    public static ArrayList<Organism> doodleBugs;
    public static ArrayList<Organism> ants;
    int[] grid;//need this because each organism should know the univ grid state

    Window(int numDoodle, int numAnts, int timeStep){
        this.numDoodle = numDoodle;
        this.numAnts = numAnts;
        this.timeStep = timeStep;

        grid = new int[gridX * gridY];//now we have an initial 0 filled empty world
        doodleBugs = new ArrayList<>();//accounting for maximal insect population in init
        ants = new ArrayList<>();

        for (int i = 0; i < this.numDoodle; i++) {//create and spawn the doodleBugs
            doodleBugs.add(i, new DoodleBug(gridX,gridY,grid));
            grid = doodleBugs.get(i).spawn();
        }
        for (int i = 0; i < this.numAnts; i++) {//create and spawn the ants
            ants.add(i, new Ant(gridX,gridY,grid));
            grid = ants.get(i).spawn();
        }
    }

    String makeStringyGrid(int[] grid) {
        String stringyGrid = "";
        for (int i = 1; i < grid.length; i++) {
            if (grid[i] == 1){//DOODLE
                stringyGrid = stringyGrid.concat("D");
            } else if (grid[i] == 2){//ANT
                stringyGrid = stringyGrid.concat("A");
            } else {
                stringyGrid = stringyGrid.concat(" ");
            }
            if (i % 20 == 0){
                stringyGrid = stringyGrid.concat("\n");
            }
        }
        return stringyGrid;
    }

    private void drawString(Graphics g, String text, int x, int y) {
        //special function simply allows for multiple lines (for the grid output)
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void paint(Graphics g) {//one pass is a time step
        //time-step dependent on thread. Good enough our purposes
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Monospaced",Font.BOLD+Font.PLAIN, 20);
        g2.setFont(font);
        g2.setColor(Color.black);
        drawString(g,makeStringyGrid(grid) ,50,70);

        for (int i = 0; i < doodleBugs.size() ; i++) {
            grid = doodleBugs.get(i).move(grid);
        }
        for (int i = 0; i < ants.size(); i++) {
            grid = ants.get(i).move(grid);
        }

        try {Thread.sleep(timeStep);} catch (Exception ex){}
        repaint();
    }
}
