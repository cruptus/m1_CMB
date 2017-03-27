package fr.univcorse.m1.elbaz;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<Aeroport> aeroports;

    public Manager () {
        this.aeroports = new ArrayList<>();
    }

    public List<Aeroport> getAeroports() {
        return aeroports;
    }

    public void coupler(Aeroport depart, int porteD, Aeroport arrive, int porteA, float duree) {
        depart.couplage(porteD, arrive, porteA, duree);
    }

    public void list(Aeroport... aeroports) {
        this.aeroports = new ArrayList<>();
        for (int i = 0; i < aeroports.length; i++)
            this.aeroports.add(aeroports[i]);
    }

    public void simuler () {
        for (Aeroport aeroport: this.aeroports) {
            aeroport.avancerTemps();
            aeroport.changerPortes();
            aeroport.envoyerVol();
        }
    }
}
