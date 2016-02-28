package arkanoid;

/**
 * Created by Юлия on 16.02.2016.
 */
public class Timer extends Thread{
    Field field;
    Boolean tic_tac;
    public void setField(Field field){

        this.field = field;
    }
    public void run(){
        tic_tac = true;
        int i = 1;
        int sleep;
        while (tic_tac){
            try {
                sleep = 5-i/5000;
                if (sleep<1) sleep = 1;
                Thread.sleep(sleep);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                field.step();
            } catch (Exception e) {
               System.out.println("Something happen");
                if(field.stillPlaying()){
                    System.out.println("Something BAD happen");
                    e.printStackTrace();
                }
                else{
                    System.out.println("Oh it's juxt game over");
                    tic_tac=false;
                }
            }

        }
    }
}
