package arkanoid;

import javax.swing.*;

/**
 * Created by Юлия on 23.02.2016.
 */
public class Game {
    public void createAndShow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 600);

        MyPanel panel = new MyPanel(this, 4, 7);
        //panel.setLayout(null);
        frame.addKeyListener(panel);
        frame.add(panel);
        //  frame.pack();
        frame.setVisible(true);
    }
}
