package com.bug;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Window extends JPanel {
    final int gridX = 20;
    final int gridY = 20;
    final int numDoodle;
    final int numAnts;
    final int timeStep;
    private static Organism[][] univGrid;//need this because each organism should know the univ grid state

    Window(int numDoodle, int numAnts, int timeStep){
        this.numDoodle = numDoodle;
        this.numAnts = numAnts;
        this.timeStep = timeStep;

        univGrid = new Organism[gridX][gridY];//now we have an initial 0 filled empty 'world'

        for (int i = 0; i < this.numDoodle; i++) {//create and spawn the doodleBugs
            int gridXpos = ThreadLocalRandom.current().nextInt(0, gridX - 1);
            int gridYpos = ThreadLocalRandom.current().nextInt(0, gridY - 1);
            if (get(gridXpos,gridYpos) == null) {
            set(gridXpos,gridYpos, new DoodleBug(this, gridXpos,gridYpos));
            }
        }

        for (int i = 0; i < this.numAnts; i++) {//create and spawn the ants
            int gridXpos = ThreadLocalRandom.current().nextInt(0, gridX - 1);
            int gridYpos = ThreadLocalRandom.current().nextInt(0, gridY - 1);
            if (get(gridXpos,gridYpos) == null) {
            set(gridXpos,gridYpos, new Ant(this, gridXpos,gridYpos));
            }
        }
    }

    String makeStringyGrid() {
        String stringyGrid = "";//store a string to be output
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                if (univGrid[i][j] instanceof DoodleBug) {//DOODLE
                    stringyGrid = stringyGrid.concat("D");
                } else if (univGrid[i][j] instanceof Ant) {//ANT
                    stringyGrid = stringyGrid.concat("A");
                } else if (univGrid[i][j] == null){
                    stringyGrid = stringyGrid.concat("_");
                }
            }
            stringyGrid = stringyGrid.concat("\n");//this sets new line at every 20
        }
        return stringyGrid;
    }

    private void drawString(Graphics g, String text, int x, int y) {
        //special function simply allows for multiple lines (for the grid output)
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void paint(Graphics g) {//one pass is a time step
        //time-step dependent on thread. Good enough
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Monospaced",Font.BOLD+Font.PLAIN, 20);//monospace
        g2.setFont(font);
        g2.setColor(Color.black);
        drawString(g,makeStringyGrid(),50,70);

        for (int i = 0; i < gridX; i++) {//reset everything
            for (int j = 0; j < gridY; j++) {
                if (get(i,j) != null) {
                    get(i, j).stop();
                }
            }
        }
        runBugs(DoodleBug.class);
        runBugs(Ant.class);

        try {Thread.sleep(timeStep);} catch (Exception ex){}//pause for the specified timestep
        repaint();
    }
    //http://stackoverflow.com/questions/9644045/passing-an-argument-to-be-used-by-instanceof
    void runBugs(Class<?> bug) {
        for (int i = 0; i < gridX; i++) {//Ant second
            for (int j = 0; j < gridY; j++) {
                if (get(i,j) != null && get(i,j).getClass().equals(bug)) {
                    get(i, j).run();
                }
            }
        }
    }

    public Organism get(int x, int y) {
        if(!inGrid(x, y))return null;
        return univGrid[x][y];
    }

    public void set (int x, int y, Organism creature) {//simple set used for Organism
            univGrid[x][y] = creature;
    }
    public boolean inGrid(int x, int y) {//checks if you are even in the grid
        if(x >= gridX || x < 0 || y >= gridY || y < 0) return false;
        return true;
    }
}
