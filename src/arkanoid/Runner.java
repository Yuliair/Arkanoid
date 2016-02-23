package arkanoid;

/**
 * Created by ���� on 20.02.2016.
 */
public class Runner {//Blade
    private int radius;
    private int x,y;
    int directX, directY;
    private final int lengthX = 1;
    private final int lengthY = 1;

    public Runner(int x, int y, int radius){
       setXY(x,y);
       setRadius(radius);
        directX=1;
        directY=1;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getRadius(){
        return radius;
    }

    public int getDirectX(){
        return directX;
    }

    public  int getDirectY(){
        return directY;
    }

    public void setDirectX(int i){
        directX = i;
    }

    public void  setDirectY(int i){
        directY = i;
    }

    public void go(){
        x=x+directX*lengthX;
        y=y+directY*lengthY;
    }

}
