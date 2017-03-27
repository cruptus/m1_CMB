package fr.univcorse.m1.elbaz;


import javax.swing.*;

public class Demo extends JFrame {

    private Aeroport nice, bastia, paris;
    private Manager manager;


    public Demo () {
        this.nice = new Aeroport("Nice", 2, 1);
        this.nice.connexion(1, 2);
        this.bastia = new Aeroport("Bastia", 2, 1);
        this.bastia.connexion(1, 2);
        this.paris = new Aeroport("Paris", 2, 2);
        this.paris.connexion(1, 2);
        this.manager = new Manager();

        this.manager.coupler(nice, 1, bastia, 1, 1);
        this.manager.coupler(nice, 2, paris, 2, 1);
        this.manager.coupler(bastia, 2, nice, 2, 1);
        this.manager.coupler(bastia, 1, paris, 1, 2);
        this.manager.coupler(paris, 2, bastia, 2, 2);
        this.manager.coupler(paris, 1, nice, 1, 1);
        this.manager.list(nice, bastia, paris);

        this.nice.ajouterVol("KTM800", 1, 1, TypeMessage.NotNullFlight);
        this.nice.ajouterVol("JKL900", 2, 1, TypeMessage.NotNullFlight);
    }

    public void toto () {
        this.manager.simuler();
    }


    public static void main(String[] args) {
        new Demo();
    }
}
