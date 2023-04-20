package VueControleur;

import java.time.LocalTime;
import static java.lang.Thread.*;
public class Clock implements Runnable{

    private int min=0;
    private int heure=0;
    private long pause=1000; 
    private double vitesse=1;

    public void init()
    {
        LocalTime now = LocalTime.now();
        heure= now.getHour();
        min = now.getMinute();
    }
    public void start() {
        new Thread(this).start();
    }

    public void setVitesse(double coef)
    {
        vitesse = (1/coef);
    }

    public void run() {
        while (true) {
            min += 1;
            if (min % 60 == 0) {
                heure += 1;
                min = 0;
            }
            if (heure % 24 == 0) {
                heure = 0;
            }
            try {
                Thread.sleep((long) ((long)pause*vitesse));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    private void wait(double d) {
    }
    public int getHeure(){return heure;}
    public int getMin(){return min;}

}
