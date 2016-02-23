package arkanoid;

/**
 * Created by Юлия on 20.02.2016.
 */
public class Racket {
    public int width;
    public int height;
    public int leftBoard;

    public Racket(int i, int j){
        width=i;
        height=j;
    }

    public void setLeftBoard(int i){
        leftBoard = i;
    }
}
