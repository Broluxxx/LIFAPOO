package modele;

import modele.environnement.varietes.Legume;


public class SimulateurEngrais {

    private Legume legume;
    private SimulateurPotager simPot;
    private int prix = 15;

    public SimulateurEngrais(SimulateurPotager _simPot) {
        simPot = _simPot;
    }

    public int getPrix(){
        return prix;
    }

    public void Utilisation(Legume l){
        if(l!=null){
            if (l.getEngrais() == false){
                l.Engrais();
                l.setEngrais(true);
                System.out.println("engrais mis");
            }
        }
    }
   
    


 

}
