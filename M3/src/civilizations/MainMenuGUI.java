package civilizations;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.io.File;

public class MainMenuGUI extends JFrame implements ActionListener {

    Civilization civ;
    Timer timer;

    JButton btnCreate        = new JButton("Create");
    JButton btnUpgrade       = new JButton("Upgrade Technologies");
    JButton btnViewStats     = new JButton("View Stats");
    JButton btnBattleResults = new JButton("Battle Results");
    JButton btnExit          = new JButton("Exit");

    JPanel panelContent;
    JLayeredPane layeredMap = new JLayeredPane();
    BattleAnimPanel battleAnim = null;

    String lastBattleReport = "No battles yet.";

    JLabel lblFood = new JLabel();
    JLabel lblWood = new JLabel();
    JLabel lblIron = new JLabel();
    JLabel lblMana = new JLabel();
    

    public MainMenuGUI(Civilization civ, Timer timer) {
        super("Civilizations");
        this.civ = civ;
        this.timer = timer;

        setPreferredSize(new Dimension(900, 600));
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // ── NORTE: botones ──
        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelNorth.setBackground(new Color(50, 50, 50));
        btnCreate.addActionListener(this);
        btnUpgrade.addActionListener(this);
        btnViewStats.addActionListener(this);
        btnBattleResults.addActionListener(this);
        panelNorth.add(btnCreate);
        panelNorth.add(btnUpgrade);
        panelNorth.add(btnViewStats);
        panelNorth.add(btnBattleResults);
        add(panelNorth, BorderLayout.NORTH);

        // ── CENTRO: mapa con animación superpuesta ──
        layeredMap.setPreferredSize(new Dimension(620, 490));

        JPanel panelMap = new JPanel(new BorderLayout());
        panelMap.setBackground(new Color(30, 30, 30));
        panelMap.setBounds(0, 0, 620, 490);

        ImageIcon iconMapa = new ImageIcon(
        	    getClass().getResource("/civilizations/mapa.png")
        );
        
        if (iconMapa.getIconWidth() > 0) {
            Image imagenEscalada = iconMapa.getImage().getScaledInstance(620, 490, Image.SCALE_SMOOTH);
            panelMap.add(new JLabel(new ImageIcon(imagenEscalada)), BorderLayout.CENTER);
        } else {
            JLabel lblMap = new JLabel("[ MAPA ]", SwingConstants.CENTER);
            lblMap.setForeground(Color.GRAY);
            lblMap.setFont(new Font("Arial", Font.BOLD, 22));
            panelMap.add(lblMap, BorderLayout.CENTER);
        }
        layeredMap.add(panelMap, JLayeredPane.DEFAULT_LAYER);
        add(layeredMap, BorderLayout.CENTER);
       

        // ── DERECHA: recursos + contenido dinámico ──
        JPanel panelRight = new JPanel(new BorderLayout());
        panelRight.setPreferredSize(new Dimension(260, 0));
        panelRight.setBackground(new Color(70, 70, 70));

        JPanel panelResources = new JPanel(new GridLayout(4, 1, 2, 2));
        panelResources.setBackground(new Color(45, 45, 45));
        panelResources.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        lblFood.setForeground(new Color(255, 200, 50));
        lblWood.setForeground(new Color(150, 255, 100));
        lblIron.setForeground(new Color(180, 180, 255));
        lblMana.setForeground(new Color(100, 200, 255));
        lblFood.setFont(new Font("Arial", Font.BOLD, 13));
        lblWood.setFont(new Font("Arial", Font.BOLD, 13));
        lblIron.setFont(new Font("Arial", Font.BOLD, 13));
        lblMana.setFont(new Font("Arial", Font.BOLD, 13));

        actualizarRecursos();

        panelResources.add(lblFood);
        panelResources.add(lblWood);
        panelResources.add(lblIron);
        panelResources.add(lblMana);
        panelRight.add(panelResources, BorderLayout.NORTH);

        panelContent = new JPanel(new BorderLayout());
        panelContent.setBackground(new Color(70, 70, 70));
        JLabel lblDefault = new JLabel("Selecciona una opción", SwingConstants.CENTER);
        lblDefault.setForeground(Color.LIGHT_GRAY);
        lblDefault.setFont(new Font("Arial", Font.PLAIN, 13));
        panelContent.add(lblDefault, BorderLayout.CENTER);
        panelRight.add(panelContent, BorderLayout.CENTER);

        add(panelRight, BorderLayout.EAST);

        // ── SUR: Exit ──
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSouth.setBackground(new Color(50, 50, 50));
        btnExit.addActionListener(this);
        panelSouth.add(btnExit);
        add(panelSouth, BorderLayout.SOUTH);

        javax.swing.Timer refreshTimer = new javax.swing.Timer(1000, e -> actualizarRecursos());
        refreshTimer.start();
    }

    private void actualizarRecursos() {
        lblFood.setText("Food:  " + civ.food);
        lblWood.setText("Wood: " + civ.wood);
        lblIron.setText("Iron:   " + civ.iron);
        lblMana.setText("Mana: " + civ.mana);
    }

    // ── MODIFICADO: ahora recibe el report y lo pasa al callback ──
    public void showBattleAnim(int aliadosInicio, int enemigosInicio,
                                int aliadosFin,   int enemigosFin,
                                String report) {
        if (battleAnim != null) {
            layeredMap.remove(battleAnim);
        }

        battleAnim = new BattleAnimPanel(
            aliadosInicio, enemigosInicio,
            aliadosFin,   enemigosFin,
            () -> {                                  // callback al terminar 5s
                layeredMap.remove(battleAnim);
                layeredMap.revalidate();
                layeredMap.repaint();
                showBattleReport(report);            // lanza el reporte solo
            }
        );

        battleAnim.setBounds(0, 0, 620, 490);        // cubre todo el mapa
        layeredMap.add(battleAnim, JLayeredPane.PALETTE_LAYER);
        layeredMap.revalidate();
        layeredMap.repaint();
        battleAnim.startAnim();
    }

    public void showBattleReport(String report) {
        lastBattleReport = report;
        displayBattleReport();
    }

    private void displayBattleReport() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(70, 70, 70));

        JLabel titulo = new JLabel("⚔ BATTLE REPORT", SwingConstants.CENTER);
        titulo.setForeground(new Color(255, 100, 100));
        titulo.setFont(new Font("Arial", Font.BOLD, 13));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        JTextArea txtReport = new JTextArea(lastBattleReport);
        txtReport.setEditable(false);
        txtReport.setBackground(new Color(50, 50, 50));
        txtReport.setForeground(Color.WHITE);
        txtReport.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtReport.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        JScrollPane scroll = new JScrollPane(txtReport);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(50, 50, 50));

        p.add(titulo, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);

        setContent(p);
    }

    private void setContent(JPanel newPanel) {
        panelContent.removeAll();
        panelContent.add(newPanel, BorderLayout.CENTER);
        panelContent.revalidate();
        panelContent.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object font = event.getSource();

        if (font == btnCreate) {
            setContent(new CreatePanel(civ));
        } else if (font == btnUpgrade) {
            setContent(new UpgradePanel(civ));
        } else if (font == btnViewStats) {
            setContent(new StatsPanel(civ));
        } else if (font == btnBattleResults) {
            displayBattleReport();
        } else if (font == btnExit) {
            timer.cancel();
            System.exit(0);
        }

        repaint();
    }
}