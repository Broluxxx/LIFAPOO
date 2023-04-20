package modele.environnement.varietes;

public class Carrotte extends Legume {

    private int TMP1;
    private int zabi;
    public Carrotte(int Temp_depart)
    {
        super(2,4,Temp_depart,3);
        zabi = 1;
    }



    @Override
    public Varietes getVariete() {
        return Varietes.carrotte;
    }

    @Override
    protected void croissance(int heure) {
        int TMP; 
        
        if(24-depart<durée_de_vie)
        {
            if(heure<durée_de_vie)
            {TMP = 24-depart + heure;}
            else
            {
            TMP = Math.abs((24-heure)-(24-depart));
            }
        }
        else{TMP = Math.abs(heure-depart);}
        if(TMP>durée_pour_recolter&&assez_soleil==true&&zabi ==1){
            setEtatrecolte(true);
            TMP1 = TMP;
            zabi += zabi;
        }
        if(TMP-durée_pour_recolter>durée_de_vie&&pretaetrerecolter==true){setMorte(true);} 
        
        if(getInfecter()==true){
        };
        if(TMP>durée_pour_recolter/2){setPousse(true);}
        check_propagation(heure);
        check_Ensoleil(heure);
        if(infecter==true&&pretaetrerecolter==true){morte = true;}
    }
}
