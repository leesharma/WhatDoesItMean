/*
 * -----------------------------------------------------------------------------
 * Main.java
 * -----------------------------------------------------------------------------
 * Created: August 30, 2018 22:54
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import javax.swing.JFrame;

public class Main {
    public static void main (String [] args) {
        JFrame frame = new JFrame("Hello World!");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Hello world! We've just launched a blank window.");
    }
}
