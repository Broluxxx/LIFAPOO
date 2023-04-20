package VueControleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import modele.SimulateurBoutique;
import modele.SimulateurPotager;

import modele.environnement.*;
import modele.environnement.Saison.Periode;
import modele.environnement.varietes.Legume;



/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 *
 */
public class VueControleurPotager extends JFrame implements Observer {
    private SimulateurPotager simulateurPotager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private SimulateurBoutique simulateurBoutique;

    private Clock montre;
    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille
    private ImageIcon icoSoleil;
    private ImageIcon icoLune;
    private ImageIcon icoPluie;
    private ImageIcon icoSalade;
    private ImageIcon icoTerre;
    private ImageIcon icoTerreLabourer;
    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoCarotte;
    private ImageIcon icoPousse;
    private ImageIcon icoMorte;
    private ImageIcon icoPasteque;
    private ImageIcon icoAubergine;
    private ImageIcon icoInsecte;

    public static final Color jaune  = new Color(252, 248, 200);
    public static final Color Jaune = jaune;

    public static final Color bleu  = new Color(103, 192, 252);
    public static final Color Bleu = bleu;

    JLabel meteo;


    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleurPotager(SimulateurPotager _simulateurPotager,Clock montreClock) {
        sizeX = simulateurPotager.SIZE_X;
        sizeY = _simulateurPotager.SIZE_Y;
        simulateurPotager = _simulateurPotager;
        simulateurBoutique = new SimulateurBoutique(simulateurPotager);

        montre = montreClock;
        chargerLesIcones();
        placerLesComposantsGraphiques();
        //ajouterEcouteurClavier(); // si besoin
    }
/*
    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                }
            }
        });
    }
*/

    private void chargerLesIcones() {
    	// image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons	
        icoSalade = chargerIcone("Images/4.png");//chargerIcone("Images/Pacman.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoTerre = chargerIcone("Images/Terre.png");
        icoTerreLabourer = chargerIcone("Images/2.png");
        icoCarotte = chargerIcone("Images/5.png");
        icoPluie =  chargerIcone("Images/rain.png");
        icoSoleil =  chargerIcone("Images/sun.png");
        icoLune = chargerIcone("Images/moon.png");
        icoPousse = chargerIcone("Images/3.png");
        icoMorte = chargerIcone("Images/1.png");
        icoPasteque = chargerIcone("Images/6.png");
        icoInsecte = chargerIcone("Images/7.png");
        icoAubergine = chargerIcone("Images/8.png");

        
    }


    public int getHour()
    {
        return montre.getHeure();
    }




    ////////SALAH . ////////

    private void placerLesCompteur()
    {
        JPanel infos_salade = new JPanel();
        JPanel met = new JPanel();
        infos_salade.setLayout(new GridLayout(8,5));

        JTextField espace = new JTextField();
        espace.setEditable(false);
        espace.setPreferredSize(new Dimension(000100, 100)); // Définit la hauteur de l'espace en pixels




        JTextField Quantité_salade = new JTextField("Quantité récoltée Salade = " + simulateurPotager.getQauntité_salade()); 
        Quantité_salade.setText("Quantité récoltée Salade = " + simulateurPotager.getQauntité_salade());
        Quantité_salade.setEditable(false);
        infos_salade.add(Quantité_salade);

        JTextField Quantité_carotte = new JTextField("Quantité récoltée Carotte = " + simulateurPotager.getQauntité_carotte()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_carotte.setEditable(false);
        infos_salade.add(Quantité_carotte);

        JTextField Quantité_pasteque = new JTextField("Quantité récoltée Pastèque = " + simulateurPotager.getQauntité_pasteque()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_pasteque.setEditable(false);
        infos_salade.add(Quantité_pasteque);

        JTextField Quantité_aubergine = new JTextField("Quantité récoltée Aubergine = " + simulateurPotager.getQauntité_aubergine()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_aubergine.setEditable(false);
        infos_salade.add(Quantité_aubergine);

        JTextField Quantité_GraineSalade = new JTextField("Quantité graine Salade = " + simulateurPotager.getGraine_salade()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_GraineSalade.setEditable(false);
        infos_salade.add(Quantité_GraineSalade);

        JTextField Quantité_GraineCarotte = new JTextField("Quantité graine Carotte = " + simulateurPotager.getGraine_carotte()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_GraineCarotte.setEditable(false);
        infos_salade.add(Quantité_GraineCarotte);

        JTextField Quantité_GrainePasteque = new JTextField("Quantité graine Pastèque = " + simulateurPotager.getGraine_pasteque()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_GrainePasteque.setEditable(false);
        infos_salade.add(Quantité_GrainePasteque);

        JTextField Quantité_GraineAubergine = new JTextField("Quantité graine Aubergine = " + simulateurPotager.getGraine_aubergine()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_GraineAubergine.setEditable(false);
        infos_salade.add(Quantité_GraineAubergine);

        JTextField Quantité_Engrais = new JTextField("Quantité Engrais = " + simulateurPotager.getNombreEngrais()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_Engrais.setEditable(false);
        infos_salade.add(Quantité_Engrais);

        JTextField Quantité_Pesticide = new JTextField("Quantité Pesticide = " + simulateurPotager.getNombrePesticide()); // TODO inclure dans mettreAJourAffichage ...
        Quantité_Pesticide.setEditable(false);
        infos_salade.add(Quantité_Pesticide);

        infos_salade.setLayout(new GridLayout(8, 2));
        infos_salade.add(Quantité_GraineSalade);
        infos_salade.add(Quantité_salade);
        infos_salade.add(Quantité_GraineCarotte);
        infos_salade.add(Quantité_carotte);
        infos_salade.add(Quantité_GrainePasteque);
        infos_salade.add(Quantité_pasteque);
        infos_salade.add(Quantité_GraineAubergine);
        infos_salade.add(Quantité_aubergine);
        infos_salade.add(Quantité_Engrais);
        infos_salade.add(Quantité_Pesticide);



                                            ////////////////////////////////////
        
        
                                            
        JPanel contenu_boutique = new JPanel();
        contenu_boutique.setLayout(new GridLayout(4, 2));

        
        JButton bouton1 = new JButton("2€", icoSalade);

        bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterGrainesSalade();
                }
            });

        contenu_boutique.add(bouton1);


      


        JButton bouton2 = new JButton("3€", icoCarotte);
        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterGrainesCarotte();
                }
            });
        contenu_boutique.add(bouton2);

        JButton bouton3 = new JButton("8€", icoPasteque);
        bouton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterGrainesPasteque();
            }
        });
        contenu_boutique.add(bouton3);

        JButton bouton4 = new JButton("5€", icoAubergine);
        bouton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterGrainesAubergine();
            }
        });
        contenu_boutique.add(bouton4);

        JButton bouton5 = new JButton("ENGRAIS : 15€");
        bouton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterEngrais();
            }
        });
        contenu_boutique.add(bouton5);

        JButton bouton6 = new JButton("PESTICIDES : 30€");
        bouton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurBoutique.acheterPesticide();
            }
        });
        contenu_boutique.add(bouton6);





        JFrame fenetreboutique = new JFrame("Boutique");
        fenetreboutique.add(contenu_boutique);
        fenetreboutique.pack();

        JButton AfficherBoutique = new JButton("Boutique");
        AfficherBoutique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fenetreboutique.setVisible(true);
            }
        });
        
        

 


       // add(infos, BorderLayout.EAST);
    //}

    //private void placer_menu_vitesse()
   // {
        JMenuBar menuBar = new JMenuBar();
        JMenu Vitesse = new JMenu("Vitesse");
        

        JMenuItem x1 = new JMenuItem("x 1");
        x1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(1); // vitesse normale (1x)
             }
        });
        
        JMenuItem x5 = new JMenuItem("x 0.5");
        x5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(0.5); // vitesse x0.5
             }
        });
        
        JMenuItem x2 = new JMenuItem("x 2");
        x2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(2); // vitesse x2
             }
        });
        
        JMenuItem x4 = new JMenuItem("x 4");
        x4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(8); // vitesse x4
             }
        });

        JMenuItem x6 = new JMenuItem("x 8");
        x6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(16); // vitesse x4
             }
        });

        JMenuItem x7 = new JMenuItem("x 16");
        x7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(32); // vitesse x4
             }
        });
        JMenuItem x8 = new JMenuItem("x 32");
        x8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                montre.setVitesse(64); // vitesse x4
             }
        });


        Vitesse.add(x5);
        Vitesse.add(x1);
        Vitesse.add(x2);
        Vitesse.add(x4);
        Vitesse.add(x6);
        Vitesse.add(x7);
        Vitesse.add(x8);

        menuBar.add(Vitesse);
        menuBar.setVisible(true);



        JButton vente = new JButton("Vendre recolte");

        vente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.Vente(); // vitesse x4
             }
        });

        
        JButton soins = new JButton("Pesticide");

        soins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.Pesticide(); 
                simulateurPotager.supprimerPesticide();
             }
        });

        JButton planter = new JButton("test");

        planter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.planter_tt(); 
             }
        });




        JTextField argent = new JTextField("Argent : "+ simulateurPotager.getQauntité_money() + " €"); // TODO inclure dans mettreAJourAffichage ...
        argent.setEditable(false);
        infos_salade.add(argent,BorderLayout.PAGE_END);



        infos_salade.add(menuBar);
       

        infos_salade.add(vente);

        infos_salade.add(soins);
        infos_salade.add(planter);

        add(infos_salade, BorderLayout.EAST); 

        infos_salade.add(AfficherBoutique);
        
        

        JTextField Temperature = new JTextField(simulateurPotager.GetMeteo() + "°");
        Temperature.setEditable(false);
        met.add(Temperature, BorderLayout.SOUTH);

        JTextField Montre = new JTextField(montre.getHeure()+":"+montre.getMin()); // TODO inclure dans mettreAJourAffichage ...
        Montre.setEditable(false);
        met.add(Montre, BorderLayout.NORTH);

        meteo = new JLabel();
        met.add(meteo);
        add(met, BorderLayout.NORTH);


        
        if (simulateurPotager.getPluie()==false) 
        {
            if(!simulateurPotager.getEnsolleillement())
            {
                meteo.setIcon(icoLune);
                met.setBackground(Bleu);
            }
            else
            {
            meteo.setIcon(icoSoleil);
            met.setBackground(Jaune);
            }   
        } 
        else 
        {
            meteo.setIcon(icoPluie);
            met.setBackground(Bleu);
        }
 
        
        
    }

    ////////
        

    private void placerLesComposantsGraphiques() {
        setTitle("A vegetable garden");
        setSize(1380, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre
        placerLesCompteur();
        //placer_menu_vitesse();

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();

                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels, BorderLayout.CENTER);

        // écouter les évènements
        ////////////// menu planter ///////////
        JPopupMenu planterMenu = new JPopupMenu();
        
        JMenuItem Carotte = new JMenuItem("Carotte");
        JMenuItem Pasteque = new JMenuItem("Pasteque");
        JMenuItem Aubergine = new JMenuItem("Aubergine");
        JMenuItem Salade = new JMenuItem("Salade");





        Salade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simulateurPotager.getGraine_salade()>0){
                    simulateurPotager.actionUtilisateur_Salade();
                }  
                System.out.println(simulateurPotager.getGraine_salade());
                }
            });
        planterMenu.add(Salade);


        ////////////////////////////////////////////////
        Carotte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simulateurPotager.getGraine_carotte()>0){
                    simulateurPotager.actionUtilisateur_Carotte();
                }
            }   
        });

        Pasteque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simulateurPotager.getGraine_pasteque()>0){
                    simulateurPotager.actionUtilisateur_Pasteque();
                }
            }
        });

        Aubergine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simulateurPotager.getGraine_aubergine()>0){
                    simulateurPotager.actionUtilisateur_Aubergine();
                }
            }
        });
       



        
        planterMenu.add(Carotte); 
        planterMenu.add(Pasteque);
        planterMenu.add(Aubergine);
        /////////// menu Action //////
        JPopupMenu actionMenu = new JPopupMenu();
        JMenuItem recolterItem = new JMenuItem("Recolter");

        recolterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.actionUtilisateur();
            }
        });

        actionMenu.add(recolterItem);

        JMenuItem engrais = new JMenuItem("Engrais");

        engrais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simulateurPotager.getNombreEngrais() > 0){
                    simulateurPotager.Engrais();
                    simulateurPotager.supprimerEngrais();
                }
                
            }
        });

        actionMenu.add(engrais);
        
    ////////
    JPopupMenu actionMenu_caseVide = new JPopupMenu();
        JMenuItem acheterCase = new JMenuItem("Acheter");

        acheterCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.achat_case();
            }
        });

        actionMenu_caseVide.add(acheterCase);
    ////////

       
        // popup menu 
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabJLabel[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(simulateurPotager.getCase(xx, yy) instanceof CaseCultivable)
                           if(simulateurPotager.getLegume(xx, yy)!=null)
                           {
                                actionMenu.show(e.getComponent(), xx, yy);
                           }
                           else
                            { planterMenu.show(e.getComponent(), xx, yy);}


                            if(planterMenu.isVisible()||actionMenu.isVisible())
                                {
                                     simulateurPotager.mise_en_place_de_la_case_action(xx, yy);
                                }

                            else
                            { if(simulateurPotager.getQauntité_money()>=simulateurPotager.getPrix_case()&&((xx>0&&xx<19)&&(yy>0&&yy<9)))
                                {actionMenu_caseVide.show(e.getComponent(), xx, yy);
                                simulateurPotager.mise_en_place_de_la_case_action(xx, yy);}
                            }
                            
                       // simulateurPotager.actionUtilisateur(xx, yy);
                    }
                });
            }
        }
    }

    

        
    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage () {

        placerLesCompteur();
        simulateurPotager.actualis_meteo(); 
        simulateurPotager.actual_Nuisible();
        simulateurPotager.Propagation_infection();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (simulateurPotager.getPlateau()[x][y] instanceof CaseCultivable) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue
                    CaseCultivable cellule = (CaseCultivable) simulateurPotager.getPlateau()[x][y];
                    Legume legume = cellule.getLegume();
                    
                    if (legume != null) {
                        if(legume.getMorte()==true){tabJLabel[x][y].setIcon(icoMorte);}
                        else
                        {
                            if(legume.getEtatrecolte()==true)
                            {
                                switch (legume.getVariete()) {
                                case salade: tabJLabel[x][y].setIcon(icoSalade); 
                                break;
                                case carrotte: tabJLabel[x][y].setIcon(icoCarotte);
                                break;
                                case pasteque: tabJLabel[x][y].setIcon(icoPasteque);
                                break;
                                case aubergine: tabJLabel[x][y].setIcon(icoAubergine);
                                break;
                                }
                            }
                            else{
                                    if(legume.getPouse()){
                                        if (legume.getInfecter()==true){
                                            tabJLabel[x][y].setIcon(icoInsecte);}
                                        else{tabJLabel[x][y].setIcon(icoPousse);}
                                    
                                    } 
                                    else{tabJLabel[x][y].setIcon(icoTerreLabourer);}
                                }
                        }
                    } 
                    else 
                    {
                        tabJLabel[x][y].setIcon(icoTerre);

                    }

                    // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
                    //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
                    //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
                } else if (simulateurPotager.getPlateau()[x][y] instanceof CaseNonCultivable) {
                    tabJLabel[x][y].setIcon(icoMur);
                } else {

                    tabJLabel[x][y].setIcon(icoVide);
                }
            }
        }
    }

    

    public void menuDemmarage() {
        JFrame frame = new JFrame("Paramètre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Selectionner la saison pour la simulation : ");
        panel.add(label);
    
        JMenuBar menuBar = new JMenuBar();
        JMenu saisonMenu = new JMenu("Saison");
        JMenuItem x1Item = new JMenuItem("Hiver");
        JMenuItem x2Item = new JMenuItem("Printemps");
        JMenuItem x3Item = new JMenuItem("Éte");
        JMenuItem x4Item = new JMenuItem("Automne");
        x1Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.setSaison(Periode.HIVER); // vitesse normale (1x)
            }
        });
        x2Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.setSaison(Periode.PRINTEMPS); // vitesse normale (1x)
            }
        });
        x3Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.setSaison(Periode.ÉTÉ); // vitesse normale (1x)
            }
        });
        x4Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateurPotager.setSaison(Periode.AUTOMNE); // vitesse normale (1x)
            }
        });

        saisonMenu.add(x1Item);
        saisonMenu.add(x2Item);
        saisonMenu.add(x3Item);
        saisonMenu.add(x4Item);
        menuBar.add(saisonMenu);
        frame.setJMenuBar(menuBar);
        panel.add(menuBar);
    
        int lancement = JOptionPane.showConfirmDialog(null, panel, "Lancement", JOptionPane.OK_CANCEL_OPTION);
        if (lancement == JOptionPane.OK_OPTION) { 
            Periode actuellPeriode = simulateurPotager.getSaison(); 
            JOptionPane.showMessageDialog(null, "La Saison est : " + actuellPeriode +  " \n \n lancement de la simulation");            
        }
    }
    












    @Override
    public void update(Observable o, Object arg) {

        mettreAJourAffichage();
        
       /////////////////////// ICI mettre l'avancement des recolte un compteur croissant /////////////////////
        ////////////////// mise a jour de l'affichage ////////////////
        
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }


    // chargement de l'image entière comme icone
    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


        return new ImageIcon(image);
    }

    // chargement d'une sous partie de l'image
    private ImageIcon chargerIcone(String urlIcone, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans urlIcone
        BufferedImage bi = getSubImage(urlIcone, x, y, w, h);
        // adapter la taille de l'image a la taille du composant (ici : 20x20)
        return new ImageIcon(bi.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    }

    private BufferedImage getSubImage(String urlIcone, int x, int y, int w, int h) {
        BufferedImage image = null;

        try {
            File f = new File(urlIcone);
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage bi = image.getSubimage(x, y, w, h);
        return bi;
    }

}
