package com.bug;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame("world");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,700);

        jf.add(new Window(5, 10, 1000));//create world with 5 doodle bugs and 1000 ants, at 200ms intervals
        jf.setVisible(true);
    }
}


