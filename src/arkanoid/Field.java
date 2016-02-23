package arkanoid;

import javax.swing.*;

/**
 * Created by Юлия on 20.02.2016.
 */
public class Field {
    public Object lock;
    int width, height;
    public Wall wall;
    Runner runner;
    Racket racket;
    public Boolean playing;
    private int scores;
    JPanel mypanel;


    public int wallBeginX, wallBeginY;

    public Field(int width, int height, int m, int n, JPanel mypanel){

        this.height=height;
        this.width=width;


        int widthwall = 3*width/4;
        int heightwall = height/4;
        wall = new Wall(m, n, widthwall,heightwall, this);


        racket = new Racket(width/5, height/20);
        racket.setLeftBoard((width-width/5)/2);
        int radius = height/50;
        runner = new Runner(width/2, height-radius-racket.height, radius);
        playing = true;
        lock = new Object();
        this.mypanel = mypanel;
        scores = 0;

        wallBeginX = (width-widthwall)/2;
        wallBeginY = (height-heightwall)/7;
    }





    public void step() throws Exception {
        //trying to go, if some bariier, change direction and go


        //check if runner touch down border or racket
        synchronized (lock) {
            if (runner.getY() >= height - racket.height - runner.getRadius())
                if (runner.getX() > racket.leftBoard && runner.getX() < racket.leftBoard + width)
                    runner.directY = -1;
                else {
                    playing = false;
                    throw new Exception("Game over");
                }
        }


        //check if runner touch boarder up left right
        if (runner.getX()<runner.getRadius()) runner.directX=1;
        if (runner.getX()>width-runner.getRadius()) runner.directX=-1;
        if (runner.getY()<runner.getRadius()) runner.directY = 1;


        checkingWall();

        runner.go();
        mypanel.repaint();

    }

    public synchronized void  racketGoLeft(){
        synchronized (lock) {
            int shift = width / 20 + 1;
            if (shift > racket.leftBoard) shift = racket.leftBoard;
            racket.setLeftBoard(racket.leftBoard - shift);

            mypanel.repaint();
        }

    }

    public void racketGoRight(){
        synchronized (lock) {
            int shift = width / 20 + 1;
            if (shift > width - racket.leftBoard - racket.width) shift = width - racket.leftBoard - racket.width;
            racket.setLeftBoard(racket.leftBoard + shift);

            mypanel.repaint();
        }
    }



        private void checkingWall() {
        if (wall.hereIsWall(runner.getX(), runner.getRadius() + runner.getY())) runner.directY = -1;
        if (wall.hereIsWall(runner.getX(), runner.getY() - runner.getRadius())) runner.directY = 1;
        if (wall.hereIsWall(runner.getX() - runner.getRadius(), runner.getY())) runner.directX = 1;
        if (wall.hereIsWall(runner.getX() + runner.getRadius(), runner.getY())) runner.directX = -1;

        for (int x = 1; x < runner.getRadius(); x++) {
            int y = (int) Math.sqrt(runner.getRadius() * runner.getRadius() - x * x);
            if (wall.hereIsWall(x + runner.getX(), y + runner.getY())) {
                runner.directY = -1;
                runner.directX = -1;
            }
            if (wall.hereIsWall(x - runner.getX(), y + runner.getY())) {
                runner.directY = -1;
                runner.directX = 1;
            }
            if (wall.hereIsWall(x + runner.getX(), y - runner.getY())) {
                runner.directY = 1;
                runner.directX = -1;
            }
            if (wall.hereIsWall(x - runner.getX(), y - runner.getY())) {
                runner.directY = 1;
                runner.directX = 1;
            }
        }
    }

    public Boolean stillPlaying(){
        return playing;
    }

    public int getScores(){
        return scores;
    }

    public Boolean getBrickExistance(int i, int j){
        if (wall.brickExistance(i,j)) return true;
        else return false;
    }

    public void scoresUp(){
        scores++;
    }





}
