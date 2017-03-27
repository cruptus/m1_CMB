package fr.univcorse.m1.elbaz;


public class Vol {


    private Aeroport origine, destination;
    private float hDepart, hArrive;
    private TypeMessage typeVol;
    private String nom;

    public Vol(Aeroport origine, Aeroport destination, float hDepart, float hArrive, TypeMessage typeVol, String nom) {
        this.origine = origine;
        this.destination = destination;
        this.hDepart = hDepart;
        this.hArrive = hArrive;
        this.typeVol = typeVol;
        this.nom = nom;
    }

    public float gethDepart() {
        return hDepart;
    }

    public float gethArrive() {
        return hArrive;
    }

    public boolean isNull() {
        return this.typeVol.equals(TypeMessage.NullFlight);
    }

    public void changerPorte (Aeroport arrive, float duree, float latence) {
        this.hDepart = this.hArrive + latence;
        this.origine = this.destination;
        this.hArrive = this.hDepart + duree;
        this.destination = arrive;
    }
}
