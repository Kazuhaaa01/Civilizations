package civilizations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StartMenuGUI extends JFrame {

    private JButton btnNuevaPartida = new JButton("⚔ Nueva Partida");
    private JButton btnCargarPartida = new JButton("📂 Cargar Partida");
    private JButton btnSalir = new JButton("🚪 Salir");

    private GameDAO gameDAO = new GameDAO();

    public StartMenuGUI() {

        super("Civilizations - Menú de Inicio");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(360, 280));
        setResizable(false);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(40, 40, 40));

        Font fuenteBotones = new Font("Arial", Font.BOLD, 13);
        Dimension tamañoBotones = new Dimension(180, 40);

        estilarBoton(btnNuevaPartida, fuenteBotones, tamañoBotones);
        estilarBoton(btnCargarPartida, fuenteBotones, tamañoBotones);
        estilarBoton(btnSalir, fuenteBotones, tamañoBotones);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel lblTitulo = new JLabel("CIVILIZATIONS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(255, 200, 50));

        gbc.gridy = 0;
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        add(btnNuevaPartida, gbc);

        gbc.gridy = 2;
        add(btnCargarPartida, gbc);

        gbc.gridy = 3;
        add(btnSalir, gbc);

        btnNuevaPartida.addActionListener(e -> gestionarNuevaPartida());
        btnCargarPartida.addActionListener(e -> gestionarCargarPartida());
        btnSalir.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void estilarBoton(JButton btn, Font f, Dimension d) {
        btn.setFont(f);
        btn.setPreferredSize(d);
        btn.setBackground(new Color(65, 65, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
    }

    private void gestionarNuevaPartida() {

        String input = JOptionPane.showInputDialog(this,
                "Introduce slot:",
                "Nueva Partida",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) return;

        try {

            int slot = Integer.parseInt(input);

            Civilization civ = new Civilization();
            civ.food = 10000;
            civ.wood = 10000;
            civ.iron = 5000;
            civ.mana = 0;

            civ.updateArmy(new ArrayList<MilitaryUnit>());

            if (gameDAO.saveGame(slot, civ)) {
                arrancarCicloDeJuego(slot, civ);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido");
        }
    }

    private void gestionarCargarPartida() {

        String input = JOptionPane.showInputDialog(this,
                "Introduce slot:",
                "Cargar Partida",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) return;

        try {

            int slot = Integer.parseInt(input);

            Civilization civ = gameDAO.loadGame(slot);

            if (civ != null) {
                arrancarCicloDeJuego(slot, civ);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido");
        }
    }

    private void arrancarCicloDeJuego(int saveSlot, Civilization civilization) {

        Timer timer = new Timer();

        MainMenuGUI juegoGUI = new MainMenuGUI(civilization, timer, saveSlot, gameDAO);

        TimerTask task = new TimerTask() {

            int minutosTranscurridos = 0;
            int numeroBatalla = civilization.getBattles() + 1;

            @Override
            public void run() {

                minutosTranscurridos++;

                civilization.generarRecursos();

                if (minutosTranscurridos % 3 == 0) {

                    ArrayList<MilitaryUnit> enemyArmy =
                            Main.createEnemyArmy(numeroBatalla);

                    ArrayList<MilitaryUnit> myArmy =
                            civilization.getArmy();

                    int aliadosInicio = myArmy.size();
                    int enemigosInicio = enemyArmy.size();

                    Battle battle = new Battle(myArmy, enemyArmy);
                    battle.startBattle();

                    int aliadosFin = myArmy.size();
                    int enemigosFin = enemyArmy.size();

                    String development = battle.getBattleDevelopment();
                    String summary = battle.getBattleReport(numeroBatalla);

                    String report = development + "\n" + summary;

                    civilization.updateArmy(myArmy);
                    battle.resetArmyArmor();

                    civilization.setBattles(numeroBatalla);

                    gameDAO.saveGame(saveSlot, civilization);

                    // 🔥 AQUÍ ES DONDE VA INSERTBATTLE
                    gameDAO.insertBattle(
                            saveSlot,
                            numeroBatalla,
                            summary,
                            development,
                            "UNKNOWN"
                    );

                    SwingUtilities.invokeLater(() -> {
                        juegoGUI.showBattleAnim(
                                aliadosInicio,
                                enemigosInicio,
                                aliadosFin,
                                enemigosFin,
                                report
                        );
                    });

                    numeroBatalla++;
                }
            }
        };

        timer.schedule(task, 60000, 60000);

        juegoGUI.setVisible(true);
        this.dispose();
    }
}