package arkanoid;

import java.util.Arrays;

/**
 * Created by Юлия on 20.02.2016.
 */
public class Wall {

    private int[][] wall;
    private int distVer, distHor;
    private int brickWidth, brickHeight;
    private int width, height;
    private int m, n;
    private Field field;


    public  Wall(int m, int n, int width, int height, Field field){
        this.m = m;
        this.n = n;
        wall = new int[m][n];
        for (int i = 0; i < m; i++){
            Arrays.fill(wall[i], 1);
        }
        this.brickHeight = (int)(height*0.8/m);
        distVer= height/m-brickHeight;
        this.brickWidth = (int)(width*0.8/n);
        distHor=width/n-brickWidth;
        this.width=width;
        this.height = height;
        this.field = field;
    }

     public Boolean hereIsWall(int x, int y){
        return this.hereIsWallInter(x-field.wallBeginX, y-field.wallBeginY);
    }



    public Boolean hereIsWallInter(int x, int y)  {//x y from begin of wall
        //return true and killing brick if hereIsWall
        if (x<0 || y < 0 || x > width-distHor  || y>  height-distVer)
            return false;
        int i=-1;
        int j=-1;
        int tailX = x%(brickWidth+ distHor);
        int tailY = y%(brickHeight + distVer);
        if( tailX >= 0 && tailX <= brickWidth)  j = x /( brickWidth+ distHor);
        if( tailY >= 0 && tailY <= brickHeight) i = y / (brickHeight + distVer);
        if(i >= 0 && j>= 0) {
            if(wall[i][j]>0) {
                this.kill(i, j);
                field.scoresUp();
                return true;
            }
        }
       return false;
    }

    public void kill(int i, int j){
        if(i>-1&&j>-1&&i<m&&j<n)
        wall[i][j]=0;
        else System.out.println("No such brick i j  "+i+" "+j);
    }

    public Boolean brickExistance(int i, int j){
        if (wall[i][j]>0) return true;
        else return false;
    }

    public int getDistVer(){
        return distVer;
    }

    public int getDistHor(){
        return distHor;
    }

    public int getBrickWidth()
    {
        return brickWidth;
    }

    public int getBrickHeight(){
        return brickHeight;
    }


}
