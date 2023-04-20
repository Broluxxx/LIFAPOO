package modele;


import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;
import modele.environnement.Saison;
import modele.environnement.Saison.Periode;
import modele.environnement.varietes.Aubergine;
import modele.environnement.varietes.Carrotte;
import modele.environnement.varietes.Legume;
import modele.environnement.varietes.Pasteque;
import modele.environnement.varietes.Salade;
import modele.environnement.varietes.Varietes;

import java.awt.Component;
import java.awt.Point;
import java.sql.Struct;
import java.time.Period;
import java.util.Random;

import javax.swing.text.StyledEditorKit.BoldAction;


public class SimulateurBoutique implements Runnable {
    private SimulateurPesticide simulateurPesticide;
    private Salade salade;
    private Carrotte carotte;
    private Pasteque pasteque;
    private Aubergine aubergine;
    private SimulateurEngrais engrais;
    private SimulateurPesticide pesticide;
    private SimulateurPotager simulateurPotager;

    public SimulateurBoutique(SimulateurPotager sim) {
        this.simulateurPotager = sim;
        salade = new Salade(0);
        carotte = new Carrotte(0);
        pasteque = new Pasteque(0);
        aubergine = new Aubergine(0);
        engrais = new SimulateurEngrais(sim);
        pesticide = new SimulateurPesticide(sim);

    }

    public void acheterGrainesSalade() {
        if(simulateurPotager.getQauntité_money() >= salade.getprix()){
            simulateurPotager.ajouterDansGraine_salade();
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-salade.getprix());
        } 
    }

    public void acheterGrainesCarotte() {
        if(simulateurPotager.getQauntité_money() >= carotte.getprix()){
            simulateurPotager.ajouterDansGraine_carotte();
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-carotte.getprix());
        } 
    }

    public void acheterGrainesPasteque() {
        if(simulateurPotager.getQauntité_money() >= pasteque.getprix()){
            simulateurPotager.ajouterDansGraine_pasteque();
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-pasteque.getprix());
        } 
    }

    public void acheterGrainesAubergine() {
        if(simulateurPotager.getQauntité_money() >= aubergine.getprix()){
            simulateurPotager.ajouterDansGraine_aubergine();
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-aubergine.getprix());
        } 
    }

    public void acheterPesticide() {
        if(simulateurPotager.getQauntité_money() >= pesticide.getPrix()){
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-pesticide.getPrix());
            simulateurPotager.ajouterPesticide();
        }
    }

    public void acheterEngrais() {
        if(simulateurPotager.getQauntité_money() >= engrais.getPrix()){
            simulateurPotager.setQauntité_money(simulateurPotager.getQauntité_money()-engrais.getPrix());
            simulateurPotager.ajouterEngrais();
        }
    }
    


    @Override
    public void run() {

    }
}
