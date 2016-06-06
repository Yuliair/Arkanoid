package arkanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Юлия on 23.02.2016.
 */
public class Game {
    JFrame frame;
    MyPanel panel;

    public Game(){
        frame = new JFrame();

    }
    public void createAndShow() {
        panel = new MyPanel(this, Const.bricksHorizontalCount, Const.bricksVerticalCount);
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Const.gameWidth +17, Const.gameHeight+20+40);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.PINK);
        menuBar.setPreferredSize(new Dimension(Const.gameWidth,20));

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New Game");
        menuItem.setActionCommand("New Game");

        menu.add(menuItem);
        menuItem.addActionListener(panel);

        //panel.setLayout(null);
        frame.setJMenuBar(menuBar);
        frame.addMouseMotionListener(panel);
        frame.addMouseListener(panel);

        //  frame.pack();
        frame.setVisible(true);
    }

    public void closeAll(){

        panel.timer.interrupt();
        frame.dispose();
    }

    public void startNew(){
        System.out.println("I am trying!");
        createAndShow();
    }
}
