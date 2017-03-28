package fr.univcorse.m1.elbaz;

import java.util.ArrayList;
import java.util.List;

public class Porte {

    private List<Vol> vols;
    private float tempsMin, tempsMax, dureeVol;
    private int porteArrivee;
    private Aeroport aeroportArrivee;

    /**
     * Constructeur par defaut
     */
    public Porte() {
        this.vols = new ArrayList<>();
        this.tempsMin = Float.MAX_VALUE;
        this.tempsMax = Float.MAX_VALUE;
        this.aeroportArrivee = null;
        this.dureeVol = Float.MIN_VALUE;
    }


    public void couplage (Aeroport arrive, int porterArrive, float duree) {
        this.aeroportArrivee = arrive;
        this.porteArrivee = porterArrive;
        this.dureeVol = duree;
    }

    /**
     * Getter Temps min
     * @return float
     */
    public float getTempsMin() {
        return tempsMin;
    }

    public float getTempsMax() {
        return tempsMax;
    }

    public float getDureeVol() {
        return dureeVol;
    }

    public int getPorteArrivee() {
        return porteArrivee;
    }

    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }

    public Boolean isVide() {
        return this.vols.size() == 0;
    }

    private int premierNull() {
        for (int i = 0; i < this.vols.size(); i++)
            if (this.vols.get(i).isNull())
                return i;
        return -1;
    }

    public void ajouterVolAPorte(Vol vol, TypeFlight typeFlight) {
        int pos = premierNull();
        if (pos != -1)
            this.vols.remove(pos);
        this.vols.add(vol);
        if (typeFlight.equals(TypeFlight.ARRIVEE)) {
            this.tempsMax = vol.gethArrive();
            this.tempsMin = this.vols.get(0).gethArrive();
        } else {
            this.tempsMax = vol.gethDepart();
            this.tempsMin = this.vols.get(0).gethDepart();
        }
    }

    public Vol retirerVolAPorte(TypeFlight typeFlight) {
        Vol vol = this.vols.get(0);
        this.vols.remove(0);
        if (this.vols.size() == 0) {
            this.tempsMin = this.tempsMax = Float.MAX_VALUE;
        } else {
            if (typeFlight.equals(TypeFlight.ARRIVEE))
                this.tempsMin = this.vols.get(0).gethArrive();
            else
                this.tempsMin = this.vols.get(0).gethDepart();
        }
        return  vol;
    }

    public String information(TypeFlight typeFlight) {
        String tmp = "";
        if (this.vols.size() == 0)
            return "\n";

        for (Vol vol: this.vols) {
            tmp += vol.information(typeFlight) + "\n";
        }
        return tmp;
    }
}
