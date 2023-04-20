package modele;


import modele.environnement.Saison;
import modele.environnement.Saison.Periode;

import java.util.Random;



/////// YASSIN //////

public class SimulateurMeteo implements Runnable {
    final int TEMPERATURE_MIN = 10;
    final int TEMPERATURE_MAX = 50;
    final int TEMPS_ATTENTE =  3000;
    boolean pluie;
    boolean ensolleillement;
    private boolean deja_utilisé;
    private Saison saison = new Saison();

    
    public void setSaison(Periode s){saison.setSaison(s);}
    public Periode getSaison(){return saison.getSaison();}

    private SimulateurPotager simPot;
    public SimulateurMeteo(SimulateurPotager _simPot) {
    
        simPot = _simPot;
        pluie = false;
        deja_utilisé = false;
        

    }

    private void checkEnsolleillement() // a faire marcher demain zabi zabi 
    {
        switch(getSaison())
        {
            case HIVER: if(simPot.getHour()<8 || simPot.getHour()>16) ensolleillement = false; else{ensolleillement=true;} break;
            case PRINTEMPS:if(simPot.getHour()<6 || simPot.getHour()>20) ensolleillement = false;else{ensolleillement=true;} break;
            case ÉTÉ: if(simPot.getHour()<5 || simPot.getHour()>22) ensolleillement = false; else{ensolleillement=true;}break;
            case AUTOMNE: if(simPot.getHour()<6 || simPot.getHour()>20) ensolleillement = false;else{ensolleillement=true;} break;
        }
    }

    public boolean getEnsolleillement(){return ensolleillement;}

    public double getTemperatureActuelle() {
        Random random = new Random();
        return random.nextDouble() * 50; 
    }

    private double temperature = 0;

    public double getTemperature() {
        return temperature;
    }

    public double fonction_meteo(Float x)
    {
        double result = 0;
        switch(getSaison()){
        case HIVER: result= (x - 24) * (((0.000504973 * (x - 6) - 0.00363683) * (x - 15) - 0.0269444) * x + 0.00416667) + 3; break;
        case PRINTEMPS:result =  -0.0057356 * Math.pow(x, 3) + 0.135262 * Math.pow(x, 2) + 0.0615741 * x + 8;break;
        case ÉTÉ: result= (x - 24) * (((-0.00774177 * (x - 15) - 0.124537) * x) + 0.0125) + 18.3;break;
        case AUTOMNE: result=  (x - 24) * (((-0.00360082 * (x - 15) - 0.05) * x) + 0.0166667) + 13.4;break;
    }
    return result;
    }
       //double term =
       
       //( - (x * x * x) / 648.0 ) + ((x * x) / 18.0) - ((5 * x) / 18.0) + 8;
       //return term;
   // }

    

    public boolean getPluie(){return pluie;}

    private void Boolpluie(Periode p){
        int risquePrecipitation=0;
        switch(getSaison())
        {
            case HIVER: risquePrecipitation = 80;break;
            case PRINTEMPS: risquePrecipitation = 60;break;
            case ÉTÉ: risquePrecipitation = 10;break;
            case AUTOMNE: risquePrecipitation = 55; break;
        }
        Random r = new Random();
        int risque = r.nextInt(100);
        if(risque<=risquePrecipitation){this.pluie=true;}
        else{this.pluie = false;}
        
        
        

    }

    @Override
    public void run() {
        checkEnsolleillement();
        if(simPot.getHour()%4==0&&simPot.getMin()==0&&deja_utilisé==false){Boolpluie(getSaison());deja_utilisé = true;}
        else{deja_utilisé = false;}
            temperature = fonction_meteo((float)(simPot.getHour()+ ((float) simPot.getMin()/100)));

            
            
            
    }//

 

}
