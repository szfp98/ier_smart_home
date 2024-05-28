package model;

import controller.SmartHomeEnvironment;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class SmartHomeGui implements ActionListener, ChangeListener {
    private JFrame frame;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JButton button1;
    private JButton button2;
    private JSlider[] slidersForRight;

    private JTextField[] textsForRight;
    public SmartHomeGui() {
        // JFrame létrehozása
        frame = new JFrame("SmartHomeGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(2,2));
        slidersForRight = new JSlider[5];
        textsForRight = new JTextField[5];
        // Bal felső panel létrehozása 8x8-as GridLayout-tal
        topLeftPanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            topLeftPanel.add(new JButton("TL " + (i + 1)));
        }



//Külső és belső hőmérséklet mutató
//Napszak irányító
//Milyen idő van kint
//Hány ember van házban
//Elromlott légkondi
        // Jobb oldali panel létrehozása 2x6-os GridLayout-tal
         rightPanel = new JPanel(new GridLayout(5, 2));
        for (int i = 0; i < 5; i++) {
            slidersForRight[i] = new JSlider();
            textsForRight[i] = new JTextField();
        }
        textsForRight[0].setText("Time(0-24)");
        textsForRight[1].setText("Outside temp");
        textsForRight[2].setText("Weather");
        textsForRight[3].setText("N. of people");
        textsForRight[4].setText("AC working");
        for(int i = 0;i<2;i++){
            rightPanel.add(textsForRight[i]);
            rightPanel.add(slidersForRight[i]);
        }

        JPanel multiChoicePanel = new JPanel(new BorderLayout());

        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButton1 = new JRadioButton("Sunny");
        JRadioButton radioButton2 = new JRadioButton("Rainy");
        JRadioButton radioButton3 = new JRadioButton("Extra Windy");
        JRadioButton radioButton4 = new JRadioButton("Cloudy");
        JRadioButton radioButton5 = new JRadioButton("Foggy");

        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);
        group.add(radioButton5);
        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        radioPanel.add(radioButton3);
        radioPanel.add(radioButton4);
        radioPanel.add(radioButton5);

         multiChoicePanel.add(radioPanel, BorderLayout.CENTER);
        rightPanel.add(textsForRight[2]);
        rightPanel.add(multiChoicePanel);
// Alsó panel létrehozása 6x2-es GridLayout-tal
        bottomPanel = new JPanel(new GridLayout(2, 6));
        for (int i = 0; i < 12; i++) {
            bottomPanel.add(new JButton("B " + (i + 1)));
        }

        // Bal felső panel hozzáadása a JFrame-hez (WEST pozícióba)
        frame.add(topLeftPanel, BorderLayout.WEST);

        // Jobb oldali panel hozzáadása a JFrame-hez (EAST pozícióba)
        frame.add(rightPanel, BorderLayout.EAST);

        // Alsó panel hozzáadása a JFrame-hez (SOUTH pozícióba)
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // JFrame megjelenítése
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // GUI alkalmazás indítása
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SmartHomeGui();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
