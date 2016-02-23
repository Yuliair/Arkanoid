package arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Юлия on 16.02.2016.
 */
public class MyPanel extends JPanel implements KeyListener {
    Game game;
    Field field;
    private int m, n;
    private int sizeS;
    private int phraze;

    private final int width = 300;
    private final int height = 500;



    MyPanel(Game game, int m, int n) {
        this.game = game;
        this.field = new Field(width, height, m , n, this);
        this.m = m;
        this.n = n;
//        this.addKeyListener(this);

        phraze = -2;

        Timer timer = new Timer();
        timer.setField(this.field);
        timer.start();


    }

    public void paintComponent(Graphics g) {
        int textLong = 100;
        int otstup2 = 30;
        int otstup = 30;

        super.paintComponent(g);
        int brickwidth = field.wall.getBrickWidth();
        int brickHeight = field.wall.getBrickHeight();
        int dictHor = field.wall.getDistHor();
        int dictVer = field.wall.getDistVer();
        int wallBegX = field.wallBeginX;
        int wallBegY = field.wallBeginY;


        //int size =
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);


        //paint bricks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.field.getBrickExistance(i, j)) {
                   g.setColor(getColor(1));
                    g.fillRect(wallBegX+ j * (brickwidth+dictHor), wallBegY+ i * (brickHeight+dictVer), brickwidth, brickHeight);
                }
            }
        }

        //paint racket

        g.fillRect(field.racket.leftBoard, field.height-field.racket.height, field.racket.width, field.racket.height);



        g.setColor(Color.BLACK);
        g.fillOval(field.runner.getX(), field.runner.getY(), field.runner.getRadius(), field.runner.getRadius());



    }
    private Color getColor(int i) {
        if (i==0) return Color.white;
        else return Color.orange;
    }

    private Color getColor2(int i) {
        if (i==0) return Color.white;
        int r = i/1000000;
       // System.out.println(r+ " -r");
        i = i% 1000000;
        int g = i/1000;
       // System.out.println(g+ " -g");
        i = i & 1000;
           // System.out.println(i);
        int b = i%50;

        return new Color(r,g,b);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("WHAT");
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            //2
            try {
                field.racketGoLeft();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            //3
            try {
                field.racketGoRight();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
            //System.out.println("WHAT");
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //2
                try {
                    field.racketGoLeft();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //3
                try {
                    field.racketGoRight();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
