package com.bug;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wahid on 10/2/2016.
 */
public class Window extends JPanel{
    int lastPos;
    Ant ant1;//TODO: TESTING
    static final int gridX = 20;
    static final int gridY = 20;
    int[] grid;//need this because each organism should know the grid state

    Window(){
        ant1 = new Ant(gridX, gridY);
        ant1.spawn();//spawn 1 organism at random coordinates
    }

    String makeStringyGrid(int[] grid) {
        String stringyGrid = "";
        for (int i = 1; i < grid.length; i++) {
            if (grid[i] == 1){
                stringyGrid = stringyGrid.concat("\u03DA");
            } else {
                stringyGrid = stringyGrid.concat("\u2610");
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

    public void paint(Graphics g) {
        //timestep is dependent on thread. Good enough our purposes
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN, 20);
        g2.setFont(font);
        g2.setColor(Color.black);
        drawString(g,makeStringyGrid(ant1.getGrid()) ,50,70);

        ant1.move();
        try {Thread.sleep(10);} catch (Exception ex){}
        repaint();
    }

}
