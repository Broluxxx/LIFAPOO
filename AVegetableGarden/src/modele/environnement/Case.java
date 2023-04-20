/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.environnement;

import modele.SimulateurPotager;
import modele.environnement.varietes.Legume;


public abstract class Case implements Runnable {
    protected SimulateurPotager simulateurPotager;
    
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y];

    private int précipitations = 0; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private int ensolleillement = 0;

    public void incremente_ensolleillement() {
        ensolleillement++;
    }

    public void incremente_précipitations() {
        précipitations++;
    }
    
    public int Get_précipitations(){
        return précipitations;
    }

    public int Get_ensolleillement(){
        return ensolleillement;
    }

    public void Set_ensolleillement(int ensolleillements){
        this.ensolleillement = ensolleillements;
    }

    public void Set_précipitations(int précipitation){
        this.précipitations = précipitation;
    }

    public Legume legume;

    public Case(SimulateurPotager _simulateurPotager) {
        simulateurPotager = _simulateurPotager;
    }

    public abstract void actionUtilisateur(int TMP);

    


  }
