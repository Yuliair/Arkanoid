package arkanoid;

/**
 * Created by Юлия on 10.02.2016.
 */
public class XY {
        int x, y;
        public XY(int i, int j){
            setXY(i ,j);
        }
    public void setY(int y){
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
        public void setXY(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }

}
