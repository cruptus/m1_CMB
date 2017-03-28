package fr.univcorse.m1.elbaz.graphic;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JPanel {

    private Label label;

    public MyLabel(String label) {
        super();
        this.setBackground(Color.white);
        this.setSize(new Dimension(200, 50));
        this.setLayout(new BorderLayout());
        this.label = new Label(label);
        this.label.setPreferredSize(new Dimension(200, 50));
        this.label.setAlignment(Label.CENTER);
        this.add(this.label, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setText (String texte) {
        this.label.setText("");
        this.label.setText(texte);
    }
}
