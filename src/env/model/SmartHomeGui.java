package model;

import controller.SmartHomeEnvironment;
import jason.asSyntax.Literal;
import jason.runtime.RuntimeServices;
import jason.runtime.RuntimeServicesFactory;
import jason.runtime.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartHomeGui implements ActionListener, ChangeListener {
    private SmartHomeEnvironment environment;
    private JFrame frame;
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JSlider[] slidersForRight;
    private JPanel counterPanel;
    private JLabel counterLabel;
    private JButton incrementButton;
    private JButton decrementButton;
    private int counter;
    private JTextField[] textsForDown;

    private JTextField[] textsForRight;
    JRadioButton sunnyButton = new JRadioButton("Sunny");
    JRadioButton extraWindyButton = new JRadioButton("Rainy");
    JRadioButton foggyButton = new JRadioButton("Extra Windy");
    JRadioButton rainyButton = new JRadioButton("Cloudy");
    JRadioButton cloudyButton = new JRadioButton("Foggy");
    JRadioButton trueButton = new JRadioButton("true");
    JRadioButton falseButton = new JRadioButton("false");
    ImageIcon door_closed = new ImageIcon("door_closed.png");
    ImageIcon curtains_closed = new ImageIcon("curtains_closed.png");
    ImageIcon curtains_open = new ImageIcon("curtains_open.png");
    ImageIcon curtains_halfopen = new ImageIcon("curtains_halfopen.png");
    ImageIcon window_open = new ImageIcon("window_open.png");
    ImageIcon light_off = new ImageIcon("light_off.png");
    ImageIcon light_on = new ImageIcon("light_on.png");
    ImageIcon wall = new ImageIcon("wall.png");
    ImageIcon wallrotated = new ImageIcon("wallrotated.png");
    ImageIcon corner_ul = new ImageIcon("corner_ul.png");
    ImageIcon corner_dr = new ImageIcon("corner_dr.png");
    ImageIcon corner_dl = new ImageIcon("corner_dl.png");
    ImageIcon corner_ur = new ImageIcon("corner_ur.png");
    ImageIcon window_closed = new ImageIcon("window_closed.png");
    ImageIcon door_open = new ImageIcon("door_open.png");
    ImageIcon ac_off = new ImageIcon("ac_off.png");
    ImageIcon ac_notworking = new ImageIcon("ac_notworking.png");
    ImageIcon ac_on = new ImageIcon("ac_on.png");

    private JLabel[] leftLabels;

    public SmartHomeGui(SmartHomeEnvironment environment) {
        // JFrame létrehozása
        frame = new JFrame("SmartHomeGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLayout(new GridBagLayout());
        this.environment = environment;


        // Bal felső panel létrehozása 8x8-as GridLayout-tal


        initWest();
        initEast();
        initSouth();


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Bal felső panel hozzáadása a JFrame-hez (WEST pozícióba)
        frame.add(topLeftPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        // Jobb oldali panel hozzáadása a JFrame-hez (EAST pozícióba)
        frame.add(rightPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        // Alsó panel hozzáadása a JFrame-hez (SOUTH pozícióba)
        frame.add(bottomPanel, gbc);

        // JFrame megjelenítése
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // GUI alkalmazás indítása
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SmartHomeGui(new SmartHomeEnvironment());
            }
        });
    }

    public void initWest() {
        topLeftPanel = new JPanel(new GridLayout(7, 7));
        leftLabels = new JLabel[49];
        for (int i = 0; i < 49; i++)
            leftLabels[i] = new JLabel();
        leftLabels[0].setIcon(corner_ul);
        for (int i = 1; i < 6; i++) {
            leftLabels[i].setIcon(wall);
            int k = 48 - i;
            leftLabels[k].setIcon(wall);

        }
        for (int i = 7; i < 49; i += 7) {
            leftLabels[i].setIcon(wallrotated);
            int k = i - 1;
            leftLabels[k].setIcon(wallrotated);
        }

        leftLabels[6].setIcon(corner_ur);
        leftLabels[42].setIcon(corner_dl);
        leftLabels[48].setIcon(corner_dr);
        leftLabels[3].setIcon(window_open);
        leftLabels[10].setIcon(curtains_open);
        leftLabels[27].setIcon(ac_on);
        leftLabels[45].setIcon(door_open);
        leftLabels[24].setIcon(light_off);
        for (int i = 0; i < 49; i++) {
            topLeftPanel.add(leftLabels[i]);
        }
    }

    //Külső és belső hőmérséklet mutató
    //Napszak irányító
//Milyen idő van kint
//Hány ember van házban
//Elromlott légkondi
    public void initSouth() {
        textsForDown = new JTextField[5];
        textsForDown[0] = new JTextField("Temperature inside");
        textsForDown[1] = new JTextField();
        textsForDown[1].addActionListener(this);
        textsForDown[2] = new JTextField("AC on");
        textsForDown[3] = new JTextField("on");
        textsForDown[3].addActionListener(this);

        bottomPanel = new JPanel(new GridLayout(2, 4));
        for (int i = 0; i < 4; i++)
            bottomPanel.add(textsForDown[i]);

    }

    public void initEast() {
        slidersForRight = new JSlider[2];
        textsForRight = new JTextField[5];
        for (int i = 0; i < 2; i++) {
            slidersForRight[i] = new JSlider();
            slidersForRight[i].addChangeListener(this);
        }
        rightPanel = new JPanel(new GridLayout(5, 2));
        for (int i = 0; i < 5; i++) {
            textsForRight[i] = new JTextField();
        }
        textsForRight[0].setText("Time(0-24)");
        textsForRight[1].setText("Outside temperature");
        textsForRight[2].setText("Weather");
        textsForRight[3].setText("Number of people");
        textsForRight[4].setText("AC working");
        for (int i = 0; i < 2; i++) {
            rightPanel.add(textsForRight[i]);
            rightPanel.add(slidersForRight[i]);
        }

        JPanel multiChoicePanel = new JPanel(new BorderLayout());

        JPanel radioPanel = new JPanel(new GridLayout(3, 2));
        ButtonGroup group = new ButtonGroup();

        sunnyButton.addActionListener(this);
        extraWindyButton.addActionListener(this);
        foggyButton.addActionListener(this);
        rainyButton.addActionListener(this);
        cloudyButton.addActionListener(this);

        group.add(sunnyButton);
        group.add(extraWindyButton);
        group.add(foggyButton);
        group.add(rainyButton);
        group.add(cloudyButton);
        radioPanel.add(sunnyButton);
        radioPanel.add(extraWindyButton);
        radioPanel.add(foggyButton);
        radioPanel.add(rainyButton);
        radioPanel.add(cloudyButton);

        multiChoicePanel.add(radioPanel, BorderLayout.CENTER);
        rightPanel.add(textsForRight[2]);
        rightPanel.add(multiChoicePanel);
        rightPanel.add((textsForRight[3]));
        counterPanel = new JPanel();
        counterPanel.setLayout(new GridLayout(1, 3));

        // Counter label létrehozása
        counter = 0;
        counterLabel = new JLabel("0", SwingConstants.CENTER);

        // Increment és decrement gombok létrehozása
        incrementButton = new JButton("+");
        decrementButton = new JButton("-");

        // ActionListener-ek hozzáadása a gombokhoz
        incrementButton.addActionListener(this);
        decrementButton.addActionListener(this);

        // Gombok és label hozzáadása a panelhez
        counterPanel.add(decrementButton);
        counterPanel.add(counterLabel);
        counterPanel.add(incrementButton);
        rightPanel.add(counterPanel);

        rightPanel.add(textsForRight[4]);

        JPanel multiChoicePanel2 = new JPanel(new BorderLayout());

        JPanel radioPanel2 = new JPanel(new GridLayout(2, 1));
        ButtonGroup group2 = new ButtonGroup();
        group2.add(trueButton);
        group2.add(falseButton);

        falseButton.addActionListener(this);
        trueButton.addActionListener(this);

        radioPanel2.add(trueButton);
        radioPanel2.add(falseButton);
        multiChoicePanel2.add(radioPanel2, BorderLayout.CENTER);
        rightPanel.add(multiChoicePanel2);

    }



  /*  leftLabels[3].setIcon(window_closed);
    leftLabels[10].setIcon(curtains_closed);
    leftLabels[27].setIcon(ac_on);
    leftLabels[45].setIcon(door_closed);
    leftLabels[24].setIcon(light_on);*/
    @Override
    public void actionPerformed(ActionEvent e) {
        counterListener(e);
        radioPanel1Listener(e);
        radioPanel2Listener(e);
    }

    public void counterListener(ActionEvent e){
        // Ellenőrzés, hogy melyik gomb lett megnyomva
        if (e.getSource() == incrementButton) {
            counter++;
        } else if (e.getSource() == decrementButton&& counter != 0) {
            counter--;
        }
        // Counter label frissítése
        counterLabel.setText(String.valueOf(counter));
    }
public void radioPanel1Listener(ActionEvent e){
        if(e.getSource() == sunnyButton){
           /* try {
                RuntimeServicesFactory rsf = new RuntimeServicesFactory();
                RuntimeServices rs = RuntimeServicesFactory.get().get()
                rs.getAgentSnapshot("window_agent").getTS().getC().addAchvGoal(Literal.parseLiteral("openWindow"), null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/
        }
        if(e.getSource() == extraWindyButton){

        }
        if(e.getSource() == foggyButton){

        }
        if(e.getSource()== rainyButton){
            try {
                RuntimeServices rs = RuntimeServicesFactory.get();
                rs.getAgentSnapshot("window_controller").getTS().getC().addAchvGoal(Literal.parseLiteral("closeWindow"), null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == cloudyButton){
        }
}
public void radioPanel2Listener(ActionEvent e){
        if (e.getSource()== trueButton){
            leftLabels[27].setIcon(ac_off);
        }
        if(e.getSource() == falseButton){
            leftLabels[27].setIcon(ac_notworking);
        }
}
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()== slidersForRight[0])
        {


        }
        if (e.getSource() == slidersForRight[1]){

        }
    }
    public void updateGui(){
        boolean windowState = environment.getModel().getDeviceState("window");
        leftLabels[3].setIcon(windowState ? window_open : window_closed );
    }
}

/*package model;

import controller.SmartRoom;
import jason.asSyntax.*;
import jason.environment.*;
import jason.runtime.RuntimeServicesInfraTier;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
public class SmartHomeGui extends JFrame implements ActionListener, ChangeListener {
    private JPanel topLeftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JSlider[] slidersForRight;
    private JPanel counterPanel;
    private JLabel counterLabel;
    private JButton incrementButton;
    private JButton decrementButton;
    private int counter;
    private JTextField[] textsForDown;
    private JTextField[] textsForRight;
    private JRadioButton weatherButton1 = new JRadioButton("Sunny");
    private JRadioButton weatherButton2 = new JRadioButton("Rainy");
    private JRadioButton weatherButton3 = new JRadioButton("Extra Windy");
    private JRadioButton weatherButton4 = new JRadioButton("Cloudy");
    private JRadioButton weatherButton5 = new JRadioButton("Foggy");
    private JRadioButton trueButton = new JRadioButton("true");
    private JRadioButton falseButton = new JRadioButton("false");
    private JLabel[] leftLabels;
    private SmartRoom environment;

    public SmartHomeGui() {
        environment = new SmartRoom();
        initializeUI();
        initializeAgents();
        startAgents();
    }

    private void initializeUI() {
        // JFrame létrehozása
        setTitle("SmartHomeGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLayout(new GridBagLayout());

        initWest();
        initEast();
        initSouth();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(topLeftPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        add(rightPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(bottomPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartHomeGui());
    }

    private void initWest() {
        topLeftPanel = new JPanel(new GridLayout(7, 7));
        leftLabels = new JLabel[49];
        for (int i = 0; i < 49; i++)
            leftLabels[i] = new JLabel();
        leftLabels[0].setIcon(new ImageIcon("corner_ul.png"));
        for (int i = 1; i < 6; i++) {
            leftLabels[i].setIcon(new ImageIcon("wall.png"));
            int k = 48 - i;
            leftLabels[k].setIcon(new ImageIcon("wall.png"));
        }
        for (int i = 7; i < 49; i += 7) {
            leftLabels[i].setIcon(new ImageIcon("wallrotated.png"));
            int k = i - 1;
            leftLabels[k].setIcon(new ImageIcon("wallrotated.png"));
        }
        leftLabels[6].setIcon(new ImageIcon("corner_ur.png"));
        leftLabels[42].setIcon(new ImageIcon("corner_dl.png"));
        leftLabels[48].setIcon(new ImageIcon("corner_dr.png"));
        leftLabels[3].setIcon(new ImageIcon("window_open.png"));
        leftLabels[10].setIcon(new ImageIcon("curtains_open.png"));
        leftLabels[27].setIcon(new ImageIcon("ac_on.png"));
        leftLabels[45].setIcon(new ImageIcon("door_open.png"));
        leftLabels[24].setIcon(new ImageIcon("light_off.png"));
        for (int i = 0; i < 49; i++) {
            topLeftPanel.add(leftLabels[i]);
        }
    }

    private void initSouth() {
        textsForDown = new JTextField[5];
        textsForDown[0] = new JTextField("Temperature inside");
        textsForDown[1] = new JTextField();
        textsForDown[1].addActionListener(this);
        textsForDown[2] = new JTextField("AC on");
        textsForDown[3] = new JTextField("on");
        textsForDown[3].addActionListener(this);

        bottomPanel = new JPanel(new GridLayout(2, 4));
        for (int i = 0; i < 4; i++)
            bottomPanel.add(textsForDown[i]);
    }

    private void initEast() {
        slidersForRight = new JSlider[2];
        textsForRight = new JTextField[5];
        for (int i = 0; i < 2; i++) {
            slidersForRight[i] = new JSlider();
            slidersForRight[i].addChangeListener(this);
        }
        rightPanel = new JPanel(new GridLayout(5, 2));
        for (int i = 0; i < 5; i++) {
            textsForRight[i] = new JTextField();
        }
        textsForRight[0].setText("Time(0-24)");
        textsForRight[1].setText("Outside temperature");
        textsForRight[2].setText("Weather");
        textsForRight[3].setText("Number of people");
        textsForRight[4].setText("AC working");
        for (int i = 0; i < 2; i++) {
            rightPanel.add(textsForRight[i]);
            rightPanel.add(slidersForRight[i]);
        }

        JPanel multiChoicePanel = new JPanel(new BorderLayout());
        JPanel radioPanel = new JPanel(new GridLayout(3, 2));
        ButtonGroup group = new ButtonGroup();

        weatherButton1.addActionListener(this);
        weatherButton2.addActionListener(this);
        weatherButton3.addActionListener(this);
        weatherButton4.addActionListener(this);
        weatherButton5.addActionListener(this);

        group.add(weatherButton1);
        group.add(weatherButton2);
        group.add(weatherButton3);
        group.add(weatherButton4);
        group.add(weatherButton5);
        radioPanel.add(weatherButton1);
        radioPanel.add(weatherButton2);
        radioPanel.add(weatherButton3);
        radioPanel.add(weatherButton4);
        radioPanel.add(weatherButton5);

        multiChoicePanel.add(radioPanel, BorderLayout.CENTER);
        rightPanel.add(textsForRight[2]);
        rightPanel.add(multiChoicePanel);
        rightPanel.add(textsForRight[3]);
        counterPanel = new JPanel();
        counterPanel.setLayout(new GridLayout(1, 3));

        counter = 0;
        counterLabel = new JLabel("0", SwingConstants.CENTER);

        incrementButton = new JButton("+");
        decrementButton = new JButton("-");

        incrementButton.addActionListener(this);
        decrementButton.addActionListener(this);

        counterPanel.add(decrementButton);
        counterPanel.add(counterLabel);
        counterPanel.add(incrementButton);
        rightPanel.add(counterPanel);

        rightPanel.add(textsForRight[4]);

        JPanel multiChoicePanel2 = new JPanel(new BorderLayout());
        JPanel radioPanel2 = new JPanel(new GridLayout(2, 1));
        ButtonGroup group2 = new ButtonGroup();
        group2.add(trueButton);
        group2.add(falseButton);

        falseButton.addActionListener(this);
        trueButton.addActionListener(this);

        radioPanel2.add(trueButton);
        radioPanel2.add(falseButton);
        multiChoicePanel2.add(radioPanel2, BorderLayout.CENTER);
        rightPanel.add(multiChoicePanel2);
    }

    private void initializeAgents() {
        try {
            // JSON fájl betöltése és feldolgozása saját implementációval
            File file = new File("actors_config.json");
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String jsonString = sb.toString();
            Map<String, Object> jsonMap = parseJson(jsonString);
            List<Map<String, Object>> actors = (List<Map<String, Object>>) jsonMap.get("actors");

            for (Map<String, Object> actor : actors) {
                String agentName = (String) actor.get("name");
                String aslFile = (String) actor.get("type") + ".asl";
                List<Map<String, String>> actions = (List<Map<String, String>>) actor.get("actions");

                Infrastructure infra = new CentralisedMASLauncherInfraTier();
                infra.startMAS();
                rs.createAgent(agentName, aslFile, null, null, null, null);
                rs.startAgent(agentName);

                for (Map<String, String> action : actions) {
                    rs.invoke(agentName, new Structure(action.get("action")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> parseJson(String jsonString) {
        // Egyszerű JSON parser implementáció (egyedi megvalósítás, használhatsz JSON library-t is)
        // Itt egy példa hogyan dolgozhatsz a JSON stringekkel saját implementációval
        // Ez az egyszerű példa feltételezi, hogy a JSON szerkezet egyszerű és jól formázott
        Map<String, Object> jsonMap = new HashMap<>();
        jsonString = jsonString.trim().substring(1, jsonString.length() - 1); // Remove outer curly braces
        String[] keyValuePairs = jsonString.split(",");
        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            String key = entry[0].trim().replaceAll("\"", "");
            String value = entry[1].trim().replaceAll("\"", "");
            jsonMap.put(key, value);
        }
        return jsonMap;
    }

    private void startAgents() {
        // Lehetőséged van további init kód hozzáadására, ha szükséges
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Eventek kezelése
        counterListener(e);
        radioPanel1Listener(e);
        radioPanel2Listener(e);
    }

    private void counterListener(ActionEvent e) {
        if (e.getSource() == incrementButton) {
            counter++;
        } else if (e.getSource() == decrementButton && counter != 0) {
            counter--;
        }
        counterLabel.setText(String.valueOf(counter));
    }

    private void radioPanel1Listener(ActionEvent e) {
        // RadioPanel1 eventek kezelése
    }

    private void radioPanel2Listener(ActionEvent e) {
        if (e.getSource() == trueButton) {
            leftLabels[27].setIcon(new ImageIcon("ac_off.png"));
        }
        if (e.getSource() == falseButton) {
            leftLabels[27].setIcon(new ImageIcon("ac_notworking.png"));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // StateChanged eventek kezelése
    }
}

// Segédosztályok JSON kezeléséhez
class ActorsConfig {
    private List<Actor> actors;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}

class Actor {
    private String type;
    private String name;
    private List<Action> actions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}

class Action {
    private String time;
    private String action;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
*/
