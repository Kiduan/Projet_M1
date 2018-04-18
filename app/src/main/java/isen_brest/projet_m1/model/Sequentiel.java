package isen_brest.projet_m1.model;

import java.util.ArrayList;
import java.util.List;

public class Sequentiel {

    // --------------------Variables--------------------
    // -------------------------------------------------
    private String nomSequentiel;
    private String iconeSequentiel;
    private String defilement;
    private Boolean voix;
    private List<Etape> etapeList;

    // --------------------Constructeur--------------------
    // ----------------------------------------------------
    public Sequentiel(){
        nomSequentiel = "";
        iconeSequentiel = "";
        defilement = "";
        voix = null;
        etapeList = new ArrayList<>();
    }

    // --------------------Getters & Setters--------------------
    // ---------------------------------------------------------
    public String getNomSequentiel() {
        return nomSequentiel;
    }

    public void setNomSequentiel(String nomSequentiel) {
        this.nomSequentiel = nomSequentiel;
    }

    public String getIconeSequentiel() {
        return iconeSequentiel;
    }

    public void setIconeSequentiel(String iconeSequentiel) {
        this.iconeSequentiel = iconeSequentiel;
    }

    public String getDefilement() {
        return defilement;
    }

    public void setDefilement(String defilement) {
        this.defilement = defilement;
    }

    public Boolean getVoix() {
        return voix;
    }

    public void setVoix(Boolean voix) {
        this.voix = voix;
    }

    public List<Etape> getEtapeList() {
        return etapeList;
    }

    public void setEtapeList(List<Etape> etapeList) {
        this.etapeList = etapeList;
    }

    // --------------------Definition de la classe Etape--------------------
    // ---------------------------------------------------------------------
    public static class Etape {

        // --------------------Constructeur--------------------
        // ----------------------------------------------------
        public Etape(){
            media = "";
            nomEtape = "";
            numEtape = null;
            minuteur = null;
            videoVitesse = null;
            videoBoucle = null;
        }

        // --------------------Variables--------------------
        // -------------------------------------------------
        private String media;
        private String nomEtape;
        private Integer numEtape;
        private Integer minuteur;
        private Integer videoVitesse;
        private Boolean videoBoucle;

        // --------------------Getters & Setters--------------------
        // ---------------------------------------------------------
        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getNomEtape() {
            return nomEtape;
        }

        public void setNomEtape(String nomEtape) {
            this.nomEtape = nomEtape;
        }

        public Integer getNumEtape() {
            return numEtape;
        }

        public void setNumEtape(Integer numEtape) {
            this.numEtape = numEtape;
        }

        public Integer getMinuteur() {
            return minuteur;
        }

        public void setMinuteur(Integer minuteur) {
            this.minuteur = minuteur;
        }

        public Integer getVideoVitesse() {
            return videoVitesse;
        }

        public void setVideoVitesse(Integer videoVitesse) {
            this.videoVitesse = videoVitesse;
        }

        public Boolean getVideoBoucle() {
            return videoBoucle;
        }

        public void setVideoBoucle(Boolean videoBoucle) {
            this.videoBoucle = videoBoucle;
        }
    }

    public static Sequentiel createSeqTest()
    {
        // On crée et on remplit un objet Sequentiel
        //---------------------------------------------
        Sequentiel seqTest = new Sequentiel();
        seqTest.setNomSequentiel("Exemple de séquentiel");
        seqTest.setVoix(false);
        seqTest.setDefilement("minuteur");

        //---------------------------------------------
        Sequentiel.Etape etape1 = new Sequentiel.Etape();
        etape1.setMedia("img_2");
        etape1.setNomEtape("étape 1");
        etape1.setNumEtape(1);
        etape1.setMinuteur(15);

        Sequentiel.Etape etape2 = new Sequentiel.Etape();
        etape2.setMedia("img_4");
        etape2.setNomEtape("deuxième étape");
        etape2.setNumEtape(2);
        etape2.setMinuteur(30);

        Sequentiel.Etape etape3 = new Sequentiel.Etape();
        etape3.setMedia("img_7");
        etape3.setNomEtape("deuxième étape");
        etape3.setNumEtape(3);
        etape3.setMinuteur(10);

        //---------------------------------------------
        ArrayList<Sequentiel.Etape> etapeArrayList = new ArrayList<>();
        etapeArrayList.add(etape1);
        etapeArrayList.add(etape2);
        etapeArrayList.add(etape3);
        seqTest.setEtapeList(etapeArrayList);

        return seqTest;
    }
}
