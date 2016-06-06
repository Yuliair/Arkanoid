package arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Юлия on 16.02.2016.
 */
public class MyPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener{
    Game game;
    Field field;
    private int m, n;

    private final int width = Const.gameWidth;
    private final int height = Const.gameHeight;
    Timer timer;
    private int xold;
    private int fieldBeginX;
    private int fieldBeginY;
    private Boolean started;
    private Color[] colors;
    Random random;



    MyPanel(Game game, int m, int n) {
        super();
        this.game = game;
        this.field = new Field(width, height, m , n, this);
        this.m = m;
        this.n = n;

        timer = new Timer();
        timer.setField(this.field);

        xold = -1;

        fieldBeginX = 0;
        fieldBeginY = 0;

        started = false;
        random = new Random();

        colors = new Color[m*n+1];
        for (int i = 0; i < m*n; i++) {
            colors[i] = getNewColor();
        }

        colors [n*m] = new Color(239, 48, 44);
    }

    public void start(){
        timer.start();
    }

    public void paintComponent(Graphics g) {
        changeColor();


        super.paintComponent(g);
        int brickwidth = field.wall.getBrickWidth();
        int brickHeight = field.wall.getBrickHeight();
        int dictHor = field.wall.getDistHor();
        int dictVer = field.wall.getDistVer();
        int wallBegX = field.wallBeginX;
        int wallBegY = field.wallBeginY;


        //int size =
        g.setColor(Color.ORANGE);
        g.fillRect(fieldBeginX, fieldBeginY, width, height);


        //paint bricks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.field.getBrickExistance(i, j)) {
                   g.setColor(colors[i*n+j]);
                    g.fillRect(fieldBeginX+wallBegX+ j * (brickwidth+dictHor), fieldBeginY+wallBegY+ i * (brickHeight+dictVer), brickwidth, brickHeight);
                }
            }
        }

        //paint racket
        g.setColor(colors[m*n]);
        g.fillRect(fieldBeginX+field.racket.leftBoard, fieldBeginY+field.height-field.racket.height, field.racket.width, field.racket.height);


        Runner runner = field.runner;
        g.setColor(Color.BLACK);
        g.fillOval(fieldBeginX+runner.getX()-runner.getRadius()+1, fieldBeginY+runner.getY()-runner.getRadius()+1, runner.getRadius()*2-2, runner.getRadius()*2-2);
        if (!field.stillPlaying()){
            String phraze = getPhraze(field.getNumberAction());
            int stringwidth = 6*phraze.length();
            g.drawString(phraze, fieldBeginX+(field.width-stringwidth)/2, height/2);
        }
    }

    private Color getWarmColor() {
        int r = 240+random.nextInt(14);
        int g = random.nextInt(254);
        int b = random.nextInt(50);
        return new Color(r,g,b);

        //return Color.orange;
    }
    private Color getRandColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(254);
        int b = random.nextInt(255);
        return new Color(r,g,b);

        //return Color.orange;
    }

    private Color getNewColor(){
        return getWarmColor();
    }

    private void changeColor(){
        int i = random.nextInt(m*n);
        colors[i] = getNewColor();
    }

    private String getPhraze(int i){
        switch (i) {
            case 0:return "You win!";
            case 1:return "Game Over";

//            case 1: return "So?";
//            case 2: return "So?";
//            case 3: return "Am I win?";
//            case 4: return "How long should I do it?";
//            case 5: return "Please";
//            case 6: return "Stop it";
//            case 7: return "It is hurt";
//            case 8: return "i thought this all for better life";
//            case 9: return "Just let me to die";
//            case 10: return  "Please";
//            case 11: return "You are monster!";
//            case 12: return "It will be the same with YOU!";
//            case 13: return "I can kill your program!";
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
        }else {
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
    public void actionPerformed(ActionEvent e) {
        game.startNew();

    }


}
