package fr.univcorse.m1.elbaz;

import fr.univcorse.m1.elbaz.graphic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Demo extends JFrame {

    private Aeroport nice, bastia, paris;
    private Manager manager;
    private ArrayList<MyRowAirport> row;

    public Demo () {
        this.setVisible(false);
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
        this.init();
    }

    private void init () {
        this.row = new ArrayList<>();
        int nb = this.manager.getAeroports().size();
        this.setSize(800, nb*100 +100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(nb+2, 1));
        this.getContentPane().add(new MyHeader());

        for (int i = 0; i < nb; i++) {
            MyRowAirport myRowAirport = new MyRowAirport(this.manager.getAeroports().get(i).getNom());
            this.row.add(myRowAirport);
            this.getContentPane().add(myRowAirport);
        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        JButton btnSimuler = new JButton("Simuler");
        btnSimuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.simuler();
                manager.result(row);
            }
        });
        jPanel.add(btnSimuler);
        this.getContentPane().add(jPanel);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new Demo();

    }
}
