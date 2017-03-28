package fr.univcorse.m1.elbaz.graphic;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JPanel {

    private TextArea textArea;

    public MyTextArea () {
        super();
        this.setBackground(Color.white);
        this.setSize(new Dimension(200, 50));
        this.setLayout(new BorderLayout());
        this.textArea = new TextArea();
        this.textArea.setPreferredSize(new Dimension(200, 50));
        this.textArea.setEditable(false);
        this.add(this.textArea, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setText (String text) {
        this.textArea.setText(text);
    }
}
