package modele.environnement;

import modele.SimulateurPotager;
import modele.environnement.varietes.Aubergine;
import modele.environnement.varietes.Carrotte;
import modele.environnement.varietes.Legume;
import modele.environnement.varietes.Pasteque;
import modele.environnement.varietes.Salade;


public class CaseCultivable extends Case {

    private Legume legume;

    public CaseCultivable(SimulateurPotager _simulateurPotager) {
        super(_simulateurPotager);
    }

    @Override
    public void actionUtilisateur(int A) {
        if (legume == null) {
            if(A == 1)
            {
            legume = new Salade(simulateurPotager.getHour());
            
            }
            if(A == 2)
            {
            legume = new Carrotte(simulateurPotager.getHour());
            
            }
            if(A == 3)
            {
            legume = new Pasteque(simulateurPotager.getHour());
            }
            if(A == 4)
            {
            legume = new Aubergine(simulateurPotager.getHour());
            } 

        } else {
            if(legume instanceof Salade){
                if(legume.getMorte()==false&&legume.getEtatrecolte()==true&&legume.getInfecter()==false) {simulateurPotager.ajoutdansrecolte_salade();}
                legume = null;
            
            }
            if(legume instanceof Carrotte){
                if(legume.getMorte()==false&&legume.getEtatrecolte()==true&&legume.getInfecter()==false) {simulateurPotager.ajoutdansrecolte_carotte();}
                legume = null;
                }
            if(legume instanceof Pasteque){
                if(legume.getMorte()==false&&legume.getEtatrecolte()==true&&legume.getInfecter()==false) {simulateurPotager.ajoutdansrecolte_pasteque();}
                legume = null;
                }
            if(legume instanceof Aubergine){
                if(legume.getMorte()==false&&legume.getEtatrecolte()==true&&legume.getInfecter()==false) {simulateurPotager.ajoutdansrecolte_aubergine();}
                legume = null;
                }

            }

    }

    public Legume getLegume() {
        return legume;
    }
    

    public void setLegume(Legume l) {
        this.legume = l;
    }

    public void setDepartPropagation()
    {
        if(legume != null)
        {
            legume.setDepartPropagation();
        }
    }

    @Override
    public void run() {
        if (legume != null) {
           
            legume.nextStep(simulateurPotager.getHour());
        }

    }
}
