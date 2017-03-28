package fr.univcorse.m1.elbaz.graphic;

/**
 * Created by jelbaz on 28/03/2017.
 */
public class MyHeader extends MyRow {

    public MyHeader () {
        super();
        this.add(new MyLabel(""));
        this.add(new MyLabel("Portes d'arrivées"));
        this.add(new MyLabel("Porte de depart"));
        this.add(new MyLabel("Pas"));
    }
}
