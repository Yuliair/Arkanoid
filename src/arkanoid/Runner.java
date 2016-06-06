package arkanoid;

/**
 * Created by Юлия on 20.02.2016.
 */
public class Runner {//Blade
    private int radius;
    private int x,y;
    int directX, directY;
    private int lengthX;
    private int lengthY;
    private Field  field;

    public Runner(int x, int y, int radius, Field field){
       setXY(x,y);
       setRadius(radius);
        directX=1;
        directY =1;
        this.field = field;
        lengthY = 3;
        lengthX = 3;
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

    public void setAngle(Racket racket){
            directX = 1;
            int distFromCenter = x - (racket.leftBoard+racket.width/2);
            if (distFromCenter<0) {
                directX = -1;
                distFromCenter = -distFromCenter;
            }
            lengthX = distFromCenter *(12) /racket.width;
        }
    }



