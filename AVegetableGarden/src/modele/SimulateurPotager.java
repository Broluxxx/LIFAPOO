/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

import modele.environnement.Saison.Periode;
import modele.environnement.varietes.Legume;






import VueControleur.Clock;


public class SimulateurPotager {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;
    private int quantitéRécoltédeVarieté__1=0;
    private int quantitéRécoltédeVarieté__2=0;
    private int quantitéRécoltédeVarieté__3=0;
    private int quantitéRécoltédeVarieté__4=0;

    private int graineSalade=2;
    private int graineCarotte=1;
    private int graineAubergine=0;
    private int grainePasteque=0;

    private int pesticide = 0;
    private int engrais = 0;
    private float Money=10;
    private Clock montre;
    private Case_action case_a_traiter;
    private SimulateurMeteo simMet;
    private SimulateurPesticide simPet;
    private SimulateurEngrais simEn;
    private SimulateurNuisible simNui;
    private SimulateurBoutique simBou;
    private double Vitesse = 1;
    private float prix_case = 10;


    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private static Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées

    public Case[][] getGrille()
    {
        return grilleCases;
    }

    
    public void actual_Boutique()
    {
        Thread boutique = new Thread(simBou);
        boutique.start();
    }
    
    public void actual_Nuisible()
    {
        Thread nuisThread = new Thread(simNui);
        nuisThread.start();
    }


    public void planter_tt()
    {
            for (int i = 0; i < grilleCases.length; i++) {
                for (int j = 0; j < grilleCases[i].length; j++) {
                    if (grilleCases[i][j] instanceof CaseCultivable) { 
                        if (grilleCases[i][j] != null) {
                            grilleCases[i][j].actionUtilisateur(1);
                        }
                    }
                }
            }
    }

    public void Propagation_infection()
    {
        if(getHour()%2==0&&getMin()==0){
        for (int i = 0; i < grilleCases.length; i++) {
            for (int j = 0; j < grilleCases[i].length; j++) {
                if (grilleCases[i][j] instanceof CaseCultivable) { 
                    if (grilleCases[i][j] != null) {
                        CaseCultivable cellule = (CaseCultivable) grilleCases[i][j];
                        Legume legume = cellule.getLegume();
                        if(legume!=null)
                        {
                            if(legume.getPropagation()==true)
                            {
                               if(grilleCases[i-1][j] instanceof CaseCultivable){CaseCultivable c1 = (CaseCultivable) grilleCases[i-1][j]; c1.setDepartPropagation();} 
                               if(grilleCases[i][j-1] instanceof CaseCultivable){CaseCultivable c1 = (CaseCultivable) grilleCases[i][j-1]; c1.setDepartPropagation();} 
                               if(grilleCases[i+1][j] instanceof CaseCultivable){CaseCultivable c1 = (CaseCultivable) grilleCases[i+1][j]; c1.setDepartPropagation();} 
                               if(grilleCases[i][j+1] instanceof CaseCultivable){CaseCultivable c1 = (CaseCultivable) grilleCases[i][j+1]; c1.setDepartPropagation();} 
                            }
                        }
                    }
                }
            }
        }
    }
    }



    public void check_Ensoleil()
    {
        if(getHour()%2==0&&getMin()==0){
        for (int i = 0; i < grilleCases.length; i++) {
            for (int j = 0; j < grilleCases[i].length; j++) {
                if (grilleCases[i][j] instanceof CaseCultivable) { 
                    if (grilleCases[i][j] != null) {
                        CaseCultivable cellule = (CaseCultivable) grilleCases[i][j];
                        Legume legume = cellule.getLegume();
                        if(legume!=null)
                        {
                            if(simMet.getPluie()==false&&simMet.getEnsolleillement()==true)
                            {
                                legume.setDepartEnsoleil();
                            }
                        }
                    }
                }
            }
        }}
    }
    





 
    public void actualis_meteo()
    {
        
        Thread meteo = new Thread(simMet);
        meteo.start();
        check_Ensoleil();
       

    }
    /////////////////////////////////////// CLASS CASE ACTION SALAH //////////////////////////////////////
    public class Case_action
    {
        private int X; int Y;
        Case_action(int X,int Y){this.X = X; this.Y=Y;}
        public int getX() {
            return X;
        }
        public int getY() {
            return Y;
        }
    };
    /////////////////////////////////////// CLASS CASE ACTION SALAH //////////////////////////////////////
     
    public Legume getLegume(int xx,int yy)
    {
       if(grilleCases[xx][yy] instanceof CaseCultivable)
       {
         CaseCultivable zabi =  (CaseCultivable) grilleCases[xx][yy];
         return zabi.getLegume();
       }
       else return null;
    }

    public void mise_en_place_de_la_case_action(int XX,int YY)
    {
        case_a_traiter = new Case_action(XX, YY);
    }

    public Case getCase(int x,int y)
    {
        return grilleCases[x][y];
    }


    public void setVitess (double coef)
    {
        this.Vitesse = coef;
    }

    public double getVitesse()
    {
        return Vitesse;
    }

    public void Vente()
    {
        Money += 10*quantitéRécoltédeVarieté__1 + 20*quantitéRécoltédeVarieté__2 + 30* quantitéRécoltédeVarieté__3;
        quantitéRécoltédeVarieté__1 =0;
        quantitéRécoltédeVarieté__2 =0;
        quantitéRécoltédeVarieté__3=0;

    }

    public void Pesticide()
    {
        Thread pesticide = new Thread(simPet);
        pesticide.start();
    }

    public void Engrais()
    {
        if(grilleCases[case_a_traiter.getX()][case_a_traiter.getY()] instanceof CaseCultivable)
       {
         CaseCultivable zabi =  (CaseCultivable) grilleCases[case_a_traiter.getX()][case_a_traiter.getY()];
         Legume l = zabi.getLegume();
         simEn.Utilisation(l);
       }
        
    }
    

    public SimulateurPotager(Clock montreClock) {

        montre = montreClock;
        initialisationDesEntites();

        simMet = new SimulateurMeteo(this);
        simPet = new SimulateurPesticide(this);
        simEn = new SimulateurEngrais(this);
        simNui = new SimulateurNuisible(this);
        simBou = new SimulateurBoutique(this);

    }

    public float GetMeteo() {
        return (float) simMet.getTemperature();
    }

    public int getGraine_carotte(){
        return graineCarotte;
    }

    public int getGraine_salade(){
        return graineSalade;
    }

    public int getGraine_pasteque(){
        return grainePasteque;
    }

    public int getGraine_aubergine(){
        return graineAubergine;
    }

    public int getEngrais(){
        return simEn.getPrix();
    }

    public int getNombreEngrais(){
        return engrais;
    }

    public int getPesticide(){
        return simPet.getPrix();
    }

    public int getNombrePesticide(){
        return pesticide;
    }

    public void ajouterDansGraine_salade(){
        graineSalade++;
    }

    public void ajouterDansGraine_carotte(){
        graineCarotte++;
    }

    public void ajouterDansGraine_pasteque(){
        grainePasteque++;
    }

    public void ajouterDansGraine_aubergine(){
        graineAubergine++;
    }

    public void ajouterEngrais(){
       engrais++;
    }


    public void supprimerEngrais(){
        engrais--;
    }

    
    public void ajouterPesticide(){
        pesticide++;
     }
 
 
     public void supprimerPesticide(){
        pesticide--;
     }

    public void supprimerDansGraine_salade(){
        graineSalade--;
    }

    public void supprimerDansGraine_carotte(){
        graineCarotte--;
    }

    public void supprimerDansGraine_pasteque(){
        grainePasteque--;
    }

    public void supprimerDansGraine_aubergine(){
        graineAubergine--;
    }

    
    public int getQauntité_salade()
    {
        return quantitéRécoltédeVarieté__1;
    }

    public int getQauntité_carotte()
    {
        return quantitéRécoltédeVarieté__2;
    }

    public int getQauntité_pasteque()
    {
        return quantitéRécoltédeVarieté__3;
    }

    public int getQauntité_aubergine()
    {
        return quantitéRécoltédeVarieté__4;
    }

    public float getQauntité_money()
    {
        return Money;
    }

    public void setQauntité_money(float pessos)
    {
        Money = pessos;
    }
    public float getPrix_case()
    {
        return prix_case;
    }
    public void ajoutdansrecolte_salade()
    {
        quantitéRécoltédeVarieté__1++;
    }

    public void ajoutdansrecolte_carotte()
    {
        quantitéRécoltédeVarieté__2++;
    }

    public void ajoutdansrecolte_pasteque()
    {
        quantitéRécoltédeVarieté__3++;
    }

    public void ajoutdansrecolte_aubergine()
    {
        quantitéRécoltédeVarieté__4++;
    }
    
    public static Case[][] getPlateau() {
        return grilleCases;
    }
    
    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            addEntite(new CaseNonCultivable(this), x, 0);
            addEntite(new CaseNonCultivable(this), x, 9);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < 9; y++) {
            addEntite(new CaseNonCultivable(this), 0, y);
            addEntite(new CaseNonCultivable(this), 19, y);
        }

        addEntite(new CaseNonCultivable(this), 2, 6);
        addEntite(new CaseNonCultivable(this), 3, 6);

        //Random rnd = new Random();

        for (int x = 5; x < 15; x++) {
            for (int y = 3; y < 7; y++) {
                CaseCultivable cc = new CaseCultivable(this);
                addEntite(cc , x, y);
                //if (rnd.nextBoolean()) {
                    //cc.actionUtilisateur();
               // }

                Ordonnanceur.getOrdonnanceur().add(cc);

            }
        }

    }


public double fonction_meteo(int x)
    {
       double term = ( - (x * x * x) / 648.0 ) + ((x * x) / 18.0) - ((5 * x) / 18.0) + 8;
       return term;
    }


    public int getMin()
    {
        return montre.getMin();
    }

    public int getHour()
    {
        return montre.getHeure();
    }

    public boolean getPluie()
    {
        return simMet.getPluie();
    }



    public void achat_case(){
        addEntite(new CaseCultivable(this),case_a_traiter.getX(), case_a_traiter.getY()); 
        Money -=prix_case;
    }

    public void actionUtilisateur() {
        if (grilleCases[case_a_traiter.X][case_a_traiter.Y] != null) {
            grilleCases[case_a_traiter.X][case_a_traiter.Y].actionUtilisateur(quantitéRécoltédeVarieté__1);
        }
    }

    public void actionUtilisateur_Salade() {
        if (grilleCases[case_a_traiter.X][case_a_traiter.Y] != null) {
            grilleCases[case_a_traiter.X][case_a_traiter.Y].actionUtilisateur(1);
        }
        supprimerDansGraine_salade();
        

    }
    public void actionUtilisateur_Carotte() {
        if (grilleCases[case_a_traiter.X][case_a_traiter.Y] != null) {
            grilleCases[case_a_traiter.X][case_a_traiter.Y].actionUtilisateur(2);
        }
        supprimerDansGraine_carotte();
    }
    public void actionUtilisateur_Pasteque() {
        if (grilleCases[case_a_traiter.X][case_a_traiter.Y] != null) {
            grilleCases[case_a_traiter.X][case_a_traiter.Y].actionUtilisateur(3);
        }
        supprimerDansGraine_pasteque();
    }
    public void actionUtilisateur_Aubergine() {
        if (grilleCases[case_a_traiter.X][case_a_traiter.Y] != null) {
            grilleCases[case_a_traiter.X][case_a_traiter.Y].actionUtilisateur(4);
        }
        supprimerDansGraine_aubergine();
    }

    private void addEntite(Case e, int x, int y) {
        grilleCases[x][y] = e;
        //map.put(e, new Point(x, y));
    }


   

    public boolean getEnsolleillement(){return simMet.getEnsolleillement();}

    public void setSaison(Periode s){simMet.setSaison(s);}
    public Periode getSaison(){return simMet.getSaison();}


}
