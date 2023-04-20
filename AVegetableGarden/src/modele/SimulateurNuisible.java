package modele;

import java.util.Random;

import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;
import modele.environnement.varietes.Legume;

public class SimulateurNuisible implements Runnable{

    private SimulateurPotager simPot;
    int frequence;
    private boolean deja_utilisé;

    public SimulateurNuisible(SimulateurPotager _simPot)
    {
        this.simPot = _simPot;
        frequence = genere_frequence();
        deja_utilisé = false;

    }
    private int genere_frequence()
    {
        Random r = new Random();
        int l= r.nextInt(30)+1;
        if(l>15){return 15 ;} else {return l;}
    }

   
    

    
    private void Infection_nuisible(Case [][] grille_cases,int sizeX, int sizeY)
    {
        Random r = new Random();
        int x; 
        int y;
        do {
            x = r.nextInt(sizeX);
            y = r.nextInt(sizeY);
            
            
        } while (grille_cases[x][y] instanceof CaseNonCultivable);
        CaseCultivable cellule = (CaseCultivable) grille_cases[x][y];
        if(cellule instanceof CaseCultivable)
        {
            if(cellule.getLegume()!=null){
            Legume legume = cellule.getLegume();
            legume.setDepartPropagation();
            }
        }
        
        
    }



    public void run()
    {
        if(simPot.getHour()%4==0&&simPot.getMin()==0&&deja_utilisé==false){
            for(int i = 0;i<frequence;i++)
            {
                Infection_nuisible(simPot.getGrille(), 20, 10);
            }
            deja_utilisé = true;
            
        }
        else{deja_utilisé = false;}
    }
}
