package modele.environnement.varietes;


import VueControleur.Clock;

public abstract class Legume {
    protected int prix;
    protected boolean infecter;
    protected boolean morte;
    protected boolean pretaetrerecolter;
    protected boolean pousse;
    protected int durée_pour_recolter;
    protected int durée_de_vie;
    protected int depart;
    protected boolean engrais;
    protected boolean infect_case_a_proximiter; 
    protected int depart_infection;
    protected int depart_soleil;
    protected int depart_pluie;
    protected boolean assez_soleil; 
    protected boolean assez_pluie; 
    protected boolean soleil; 
    protected boolean pluie; 
    

    public abstract Varietes getVariete();

    public void nextStep(int heure) {
        croissance(heure);
    }
    protected Legume(int durée_pour_recolter,int durée_de_vie,int Temp_depart, int prix)
    {
        engrais = false;
        infecter = false;
        morte = false;
        pretaetrerecolter = false;
        pousse = false;
        this.durée_pour_recolter = durée_pour_recolter;
        this.durée_de_vie = durée_de_vie;
        this.depart = Temp_depart;
        infect_case_a_proximiter = false;
        assez_pluie = false;
        assez_soleil = false;
        soleil = false;
        this.infect_case_a_proximiter = false;
        this.prix = prix;
    }
    public void compteurEnsolleiment(int heure)
    {
        
    }
    protected abstract void croissance(int heure); // définir selon les conditions
    public boolean getEtatrecolte(){return pretaetrerecolter;}
    public boolean getInfecter(){return infecter; }
    public boolean getMorte(){return morte;}
    public int getDurée_Recolter(){return durée_pour_recolter;}
    public void setdurée_pour_recolter(){this.durée_pour_recolter = durée_pour_recolter / 2; this.durée_de_vie = this.durée_de_vie*2;}
    public int getDurée_vie(){return durée_de_vie;}
    public void setEtatrecolte(boolean pret){pretaetrerecolter = pret;}
    public void setInfecter(boolean contaminer){infecter = contaminer;}
    public void setMorte(boolean etatvie){morte = etatvie;} 
    public boolean getPouse(){return pousse;}
    public void setPousse(boolean etat){pousse = etat;}
    public void Engrais(){setdurée_pour_recolter();}
    public void setEngrais(boolean vite){engrais = vite;}
    public boolean getEngrais() { return engrais;}
    public boolean getPropagation(){return infect_case_a_proximiter;}
    public void setPropagation(boolean prop){this.infect_case_a_proximiter = prop;}
    public void setDepartPropagation()
    {
        Clock timer = new Clock();
        depart_infection = timer.getHeure();
        setInfecter(true);
    }   
    public void setDepartEnsoleil()
    {
        Clock timer = new Clock();
        depart_soleil = timer.getHeure();
        soleil = true;
     
    }   
    public void setDepartPluie()
    {
        Clock timer = new Clock();
        depart_pluie = timer.getHeure();
        pluie = true;
   
    }    

    public void check_Ensoleil(int heure)
    {
        int TMP;
        if(soleil == true)
        {
            
            
            if(24-depart_infection<durée_pour_recolter)
            {
                if(heure<durée_pour_recolter)
                    {
                        TMP = 24-depart_infection + heure;
                    }
                else
                    {
                        TMP = Math.abs((24-heure)-(24-depart_infection));
                    }
            }
            else{TMP = Math.abs(heure-depart_infection);}

            if(TMP>=1){depart_soleil++;
                if(depart_soleil>90) {assez_soleil = true;System.out.println(assez_soleil);}
                System.out.println(depart_soleil);}

        }
        else{TMP = 0;} 
        

    }

    public void check_pluie(int heure)
    {
        if(infecter == true)
        {
            int TMP;
            
            if(24-depart_infection<durée_pour_recolter)
            {
                if(heure<durée_pour_recolter)
                    {
                        TMP = 24-depart_infection + heure;
                    }
                else
                    {
                        TMP = Math.abs((24-heure)-(24-depart_infection));
                    }
            }
            else{TMP = Math.abs(heure-depart_infection);}

            if(TMP>=2){this.infect_case_a_proximiter = true;}

        }
    }





    public int getprix(){return prix;}
    public void setprix(int p){prix = p;}


    public void check_propagation(int heure)
    {
        if(infecter == true)
        {
            int TMP;
            
            if(24-depart_infection<durée_pour_recolter)
            {
                if(heure<durée_pour_recolter)
                    {
                        TMP = 24-depart_infection + heure;
                    }
                else
                    {
                        TMP = Math.abs((24-heure)-(24-depart_infection));
                    }
            }
            else{TMP = Math.abs(heure-depart_infection);}

            if(TMP>=2){this.infect_case_a_proximiter = true;}

        }
    }
}
