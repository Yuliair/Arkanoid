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
    private Boolean haveAim;
    private int scores;
    JPanel mypanel;

    private int actions;


    public int wallBeginX, wallBeginY;

    public Field(int width, int height, int m, int n, JPanel mypanel){

        this.height=height;
        this.width=width;


        int widthwall = 3*width/4;
        int heightwall = height/4;
        wall = new Wall(m, n, widthwall,heightwall, this);


        racket = new Racket(width/3, height/50);
        racket.setLeftBoard((width-width/3)/2);
        int radius = (height+width)/120;
        runner = new Runner(width/2, height-radius-racket.height, radius, this);
        playing = true;
        lock = new Object();
        this.mypanel = mypanel;
        scores = 0;

        wallBeginX = (width-widthwall)/2;
        wallBeginY = (height-heightwall)/7;

        haveAim = true;

    }





    public void step() throws Exception {
        //change direction and go

        //check if Game Over
        if (runner.getY()>= height-runner.getRadius()){
            playing = false;
            throw new Exception("Game over");
        }





        //check if runner touch boarder up left right
        if (runner.getX()<runner.getRadius()) runner.directX=1;
        if (runner.getX()>width-runner.getRadius()) runner.directX=-1;
        if (runner.getY()<runner.getRadius()) runner.setDirectY(1);


        //check if touch racket or wall


        checkingWallandRacket();

        runner.go();
        mypanel.repaint();

    }

    public void runnerGo(int shift){
        if (shift>0) {
            if (shift > width - runner.getX() - runner.getRadius())
                shift = width - runner.getX() - runner.getRadius();
        }
        else {
            if (-shift > runner.getX()-runner.getRadius())
                shift = -runner.getX()+runner.getRadius();
        }
        runner.setXY(runner.getX() + shift, runner.getY());
        mypanel.repaint();
    }

    public void racketGo(int i){
        if (i>0) racketGoRight(i);
        else racketGoLeft(-i);
    }

    private void  racketGoLeft(int shift){
        synchronized (lock) {
            //int shift = width / 20 + 1;
            if (shift > racket.leftBoard) shift = racket.leftBoard;
            racket.setLeftBoard(racket.leftBoard - shift);
            mypanel.repaint();
        }
    }

    private void racketGoRight(int shift){
        synchronized (lock) {
           // int shift = width / 20 + 1;
            if (shift > width - racket.leftBoard - racket.width) shift = width - racket.leftBoard - racket.width;
            racket.setLeftBoard(racket.leftBoard + shift);
            mypanel.repaint();
        }
    }

        private Boolean hereIsWallOrRacket(int x, int y){
            if (wall.hereIsWall(x,y)) return true;
            return  (x >= racket.leftBoard && x <= racket.leftBoard+racket.width &&
                     y >= height - racket.height  );

        }

        private void checkingWallandRacket() {
        if (hereIsWallOrRacket(runner.getX(), runner.getRadius() + runner.getY())) runner.setDirectY(-1);
        if (hereIsWallOrRacket(runner.getX(), runner.getY() - runner.getRadius())) runner.setDirectY(1) ;
        if (hereIsWallOrRacket(runner.getX() - runner.getRadius(), runner.getY())) runner.setDirectX(1);
        if (hereIsWallOrRacket(runner.getX() + runner.getRadius(), runner.getY())) runner.setDirectX(-1);

        for (int x = 1; x < runner.getRadius(); x++) {
            int y = (int) Math.sqrt(runner.getRadius() * runner.getRadius() - x * x);
            if (hereIsWallOrRacket(x + runner.getX(), y + runner.getY())) {
                runner.setDirectY(-1) ;
                runner.setDirectX(-1);
            }
            if (hereIsWallOrRacket(x - runner.getX(), y + runner.getY())) {
                runner.setDirectY(-1);
                runner.setDirectX(1);
            }
            if (hereIsWallOrRacket(x + runner.getX(), y - runner.getY())) {
                runner.setDirectY(1);
                runner.setDirectX(-1);
            }
            if (hereIsWallOrRacket(x - runner.getX(), y - runner.getY())) {
                runner.setDirectY(1);
                runner.setDirectX(1);
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

    public void haveNotAim(){
        haveAim = false;
        actions = 0;
    }

    public  Boolean getHaveAim(){
        return haveAim;
    }

    public void actionNext(){
        actions++;
    }
    public int getNumberAction(){
        return actions;
    }





}
