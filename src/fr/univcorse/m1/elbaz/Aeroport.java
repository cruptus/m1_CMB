package fr.univcorse.m1.elbaz;

import java.util.Random;


public class Aeroport {


    private String nom;
    private Porte[] porteDepart, porteArrivee;
    private float latence, temps;
    private int[] connexion;

    public Aeroport(String nom, int nbPortes, float latence) {
        this.nom = nom;
        this.temps = 0;
        this.latence = latence;
        this.porteArrivee = new Porte[nbPortes];
        this.porteDepart = new Porte[nbPortes];

        for (int i = 0; i < nbPortes; i++) {
            this.porteArrivee[i] = new Porte();
            this.porteDepart[i] = new Porte();
        }

        //TODO: label
    }

    public void connexion(int... porteDepart) {
        this.connexion = new int[porteDepart.length];
        for (int i = 0; i < porteDepart.length; i++) {
            Random random = new Random();
            this.connexion[i] = random.nextInt(1);
        }
    }

    public void couplage(int porteD, Aeroport arrive, int porteA, float duree) {
        if (porteD < this.porteDepart.length)
            this.porteDepart[porteD].couplage(arrive, porteA, duree);
    }

    public void ajouterVol (String nomVol, int porteD, float depart, TypeMessage typeMessage) {
        Vol vol;
        Aeroport aeroport;
        float arrive;
        if (depart >= this.temps) {
            if (this.porteDepart[porteD - 1].isVide() || this.porteDepart[porteD].getTempsMax() < depart) {
                aeroport = this.porteDepart[porteD - 1].getAeroportArrivee();
                arrive = depart + this.porteDepart[porteD - 1].getDureeVol();
                vol = new Vol(this, aeroport, depart, arrive, typeMessage, nomVol);
                this.porteDepart[porteD - 1].ajouterVolAPorte(vol, TypeFlight.DEPART);
            }
        }
    }

    public int porteMinVol (TypeFlight typeFlight) {
        float tempsMin = Float.MAX_VALUE;
        int numPorte = -1;
        Porte[] portes;
        if (typeFlight.equals(TypeFlight.DEPART))
            portes = this.porteDepart;
        else
            portes = this.porteArrivee;
        for (int i = 0; i < portes.length; i++) {
            if (portes[i].getTempsMin() < tempsMin && portes[i].getTempsMin() >= this.temps) {
                numPorte = i;
                tempsMin = portes[i].getTempsMin();
            }
        }
        return numPorte;
    }

    public Boolean ready () {
        for (int i = 0; i < this.porteArrivee.length; i++)
            if (this.porteArrivee[i].isVide())
                return false;
        return true;
    }


    public void avancerTemps() {
        int porte = porteMinVol(TypeFlight.ARRIVEE);
        if (porte >= 0 && this.ready()) {
            this.temps = Math.max(this.temps, this.porteArrivee[porte].getTempsMin());
        }
    }

    public void changerPortes() {
        Vol vol;
        int numPorteA, numPorteB;
        while ((numPorteA = this.porteMinVol(TypeFlight.ARRIVEE)) != -1 && this.porteArrivee[numPorteA].getTempsMin() <= this.temps) {
            vol = this.porteArrivee[numPorteA].retirerVolAPorte(TypeFlight.ARRIVEE);
            if (!vol.isNull()) {
                numPorteB = this.connexion[numPorteA];
                vol.changerPorte(this.porteDepart[numPorteB].getAeroportArrivee(), this.porteDepart[numPorteB].getDureeVol(), this.latence);
                this.porteDepart[numPorteB].ajouterVolAPorte(vol, TypeFlight.DEPART);
            }
        }
    }

    public void envoyerVol() {
        Vol volNull, enCours;
        Aeroport arrive;
        int numPorteA;
        float tempsArrivee;
        for (int porte = 0; porte < this.porteDepart.length; porte++) {
            arrive = this.porteDepart[porte].getAeroportArrivee();
            if (this.porteDepart[porte].isVide() || this.porteDepart[porte].getTempsMin() > (this.temps + this.latence)) {
                tempsArrivee = this.temps + this.latence + this.porteDepart[porte].getDureeVol();
                volNull = new Vol(this, arrive, this.temps + this.latence, tempsArrivee, TypeMessage.NullFlight, "Null");
                this.porteDepart[porte].ajouterVolAPorte(volNull, TypeFlight.DEPART);
            }
            while (this.porteDepart[porte].getTempsMin() <= (this.temps + this.latence)) {
                enCours = this.porteDepart[porte].retirerVolAPorte(TypeFlight.DEPART);
                numPorteA = this.porteDepart[porte].getPorteArrivee();
                arrive.porteArrivee[numPorteA].ajouterVolAPorte(enCours, TypeFlight.ARRIVEE);
            }
        }
    }
}
