package modele;
import modele.Ordonnanceur;
import modele.SimulateurPotager;
import modele.environnement.varietes.Legume;
import java.time.LocalTime;
import java.util.Random;
import modele.environnement.Case;
import modele.environnement.varietes.Legume;
import modele.environnement.CaseCultivable;

public class SimulateurPesticide implements Runnable {

    private SimulateurPotager simPot;
    private Legume legume;
    private Case[][] grilleCases = new Case[SimulateurPotager.SIZE_X][SimulateurPotager.SIZE_Y]; 
    private int prix = 30;


    public SimulateurPesticide(SimulateurPotager _simPot) {
        simPot = _simPot;
        grilleCases = _simPot.getGrille();
    }

    public int getPrix(){
        return prix;
    }

   

    public void Soigner(Case a){
        if(a instanceof CaseCultivable) {
            CaseCultivable cellule = (CaseCultivable) a;
            Legume legume = cellule.getLegume();
            if (legume != null){
                legume.setInfecter(false);
                
            }
        }
    }


    public void ProccessusSoin(){ // On va soigner toute les cases
        for (int i = 0; i < grilleCases.length; i++) {
            for (int j = 0; j < grilleCases[i].length; j++) {
                if (grilleCases[i][j] instanceof CaseCultivable) { 
                    Soigner(grilleCases[i][j]);
                }
            }
        }
    }
    
    @Override
    public void run() {
       ProccessusSoin();
        
    }

 

}
