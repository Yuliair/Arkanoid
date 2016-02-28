package arkanoid;

/**
 * Created by Юлия on 20.02.2016.
 */
public class Runner {//Blade
    private int radius;
    private int x,y;
    int directX, directY;
    private final int lengthX = 1;
    private final int lengthY = 1;
    private Field  field;

    public Runner(int x, int y, int radius, Field field){
       setXY(x,y);
       setRadius(radius);
        directX=1;
        directY =1;
        this.field = field;
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
        if (!field.getHaveAim()) field.actionNext();
    }

    public void  setDirectY(int i){
        directY = i;
        if (!field.getHaveAim()) field.actionNext();
    }

    public void go(){
        x=x+directX*lengthX;
        y=y+ directY *lengthY;
    }

}
