package fr.univcorse.m1.elbaz;

import fr.univcorse.m1.elbaz.graphic.MyRowAirport;

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
        depart.couplage(porteD -1 , arrive, porteA -1 , duree);
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

    public void result (ArrayList<MyRowAirport> rows) {
        for (int i = 0; i < this.aeroports.size(); i++) {
            this.aeroports.get(i).information(TypeFlight.ARRIVEE, rows.get(i));
            this.aeroports.get(i).information(TypeFlight.DEPART, rows.get(i));
        }
    }
}
