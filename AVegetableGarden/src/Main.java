
import VueControleur.Clock;
import VueControleur.VueControleurPotager;
import modele.Ordonnanceur;
import modele.SimulateurPotager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {



    public static void main(String[] args) {
       
       
        Clock montre = new Clock();
        montre.init();
        SimulateurPotager simulateurPotager = new SimulateurPotager(montre);
        VueControleurPotager vc = new VueControleurPotager(simulateurPotager,montre);
        vc.menuDemmarage();
        vc.setVisible(true);
        Ordonnanceur.getOrdonnanceur().addObserver(vc);
        Ordonnanceur.getOrdonnanceur().start(15);
        montre.start();


    }
}
