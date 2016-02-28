package arkanoid;

import javax.swing.*;

/**
 * Created by Юлия on 23.02.2016.
 */
public class Game {
    public void createAndShow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        MyPanel panel = new MyPanel(this, 2, 3);
        //panel.setLayout(null);
        frame.addMouseMotionListener(panel);
        frame.addMouseListener(panel);
        frame.add(panel);
        //  frame.pack();
        frame.setVisible(true);
    }
}
