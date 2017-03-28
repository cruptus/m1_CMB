package fr.univcorse.m1.elbaz.graphic;

public class MyRowAirport extends MyRow {

    private MyTextArea arrive, depart;
    private MyLabel pas;

    public MyRowAirport (String aeroport) {
        super();
        this.add(new MyLabel(aeroport));
        this.arrive = new MyTextArea();
        this.depart = new MyTextArea();
        this.pas = new MyLabel("");
        this.add(this.arrive);
        this.add(this.depart);
        this.add(this.pas);
    }

    public void setArrive (String texte) {
        System.out.print("texte :"+ texte);
        this.arrive.setText(texte);
    }

    public void setDepart (String texte) {
        this.depart.setText(texte);
    }

    public void setPas (String texte) {
        this.pas.setText(texte);
    }
}
