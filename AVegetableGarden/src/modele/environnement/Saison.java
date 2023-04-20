package modele.environnement;

public class Saison {
    public enum Periode {HIVER,PRINTEMPS,ÉTÉ,AUTOMNE}
    private Periode maSaison;
    
    public void setSaison(Periode s){maSaison = s;}
    public Periode getSaison(){return maSaison;}
}
