package arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Юлия on 16.02.2016.
 */
public class MyPanel extends JPanel implements MouseMotionListener, MouseListener{
    Game game;
    Field field;
    private int m, n;

    private final int width = 300;
    private final int height = 500;
    Timer timer;
    private int xold;
    private int fieldBeginX;
    private int fieldBeginY;
    private Boolean started;



    MyPanel(Game game, int m, int n) {
        this.game = game;
        this.field = new Field(width, height, m , n, this);
        this.m = m;
        this.n = n;

        timer = new Timer();
        timer.setField(this.field);

        xold = -1;

        fieldBeginX = 100;
        fieldBeginY = 10;

        started = false;
    }

    public void start(){
        timer.start();
    }

    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        int brickwidth = field.wall.getBrickWidth();
        int brickHeight = field.wall.getBrickHeight();
        int dictHor = field.wall.getDistHor();
        int dictVer = field.wall.getDistVer();
        int wallBegX = field.wallBeginX;
        int wallBegY = field.wallBeginY;


        //int size =
        g.setColor(Color.BLACK);
        g.drawRect(fieldBeginX, fieldBeginY, width, height);


        //paint bricks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.field.getBrickExistance(i, j)) {
                   g.setColor(getColor());
                    g.fillRect(fieldBeginX+wallBegX+ j * (brickwidth+dictHor), fieldBeginY+wallBegY+ i * (brickHeight+dictVer), brickwidth, brickHeight);
                }
            }
        }

        //paint racket
        g.fillRect(fieldBeginX+field.racket.leftBoard, fieldBeginY+field.height-field.racket.height, field.racket.width, field.racket.height);

        g.setColor(new Color(50,50,0));

        Runner runner = field.runner;
        g.setColor(Color.BLACK);
        g.fillOval(fieldBeginX+runner.getX()-runner.getRadius()+1, fieldBeginY+runner.getY()-runner.getRadius()+1, runner.getRadius()*2-2, runner.getRadius()*2-2);
        if (!field.stillPlaying()){
            String gameover=new String("Game over");
            int stringwidth = 6*gameover.length();
            g.drawString(gameover, fieldBeginX+(field.width-stringwidth)/2, height/2);
        }
        else {
            if (!field.getHaveAim()){
                String painful=getPhraze(field.getNumberAction());
                int stringwidth = 6*painful.length();
                g.drawString(painful, fieldBeginX+(field.width-stringwidth)/2, fieldBeginY+height/2);
            }
        }
    }

    private Color getColor() {
         return Color.orange;
    }

    private String getPhraze(int i){
        switch (i) {
            case 0:return "Game Over";
            case 1: return "So?";
            case 2: return "So?";
            case 3: return "Am I win?";
            case 4: return "How long should I do it?";
            case 5: return "Please";
            case 6: return "Stop it";
            case 7: return "It is hurt";
            case 8: return "i thought this all for better life";
            case 9: return "Just let me to die";
            case 10: return  "Please";
            case 11: return "You are monster!";
            case 12: return "It will be the same with YOU!";
            case 13: return "I can kill your program!";
            case 14: {
                System.out.println("I can kill your program");
                return String.valueOf(1/0);
            }
            default: return "HA HA HA";
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int xnew = e.getX();
        if(started) {
            if (xnew < 0) xold = xnew;
            else field.racketGo(xnew - xold);
            xold = xnew;
        }
        else{
            if (xnew < 0) xold = xnew;
            else {
                field.racketGo(xnew - xold);
                field.runnerGo(xnew - xold);
            }
            xold = xnew;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!started) {
            started = true;
            start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
