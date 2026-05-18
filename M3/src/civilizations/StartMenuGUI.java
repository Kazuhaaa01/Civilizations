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

        // Estilo de fuentes y botones
        Font fuenteBotones = new Font("Arial", Font.BOLD, 13);
        Dimension tamañoBotones = new Dimension(180, 40);

        estilarBotón(btnNuevaPartida, fuenteBotones, tamañoBotones);
        estilarBotón(btnCargarPartida, fuenteBotones, tamañoBotones);
        estilarBotón(btnSalir, fuenteBotones, tamañoBotones);

        // Disposición de los elementos en la ventana
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridy = 0;
        JLabel lblTitulo = new JLabel("CIVILIZATIONS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(255, 200, 50));
        add(lblTitulo, gbc);

        gbc.gridy = 1; add(btnNuevaPartida, gbc);
        gbc.gridy = 2; add(btnCargarPartida, gbc);
        gbc.gridy = 3; add(btnSalir, gbc);

        // Control de Eventos (ActionListeners)
        btnNuevaPartida.addActionListener(e -> gestionarNuevaPartida());
        btnCargarPartida.addActionListener(e -> gestionarCargarPartida());
        btnSalir.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void estilarBotón(JButton btn, Font f, Dimension d) {
        btn.setFont(f);
        btn.setPreferredSize(d);
        btn.setBackground(new Color(65, 65, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
    }

    private void gestionarNuevaPartida() {
        String input = JOptionPane.showInputDialog(this, "Introduce el número de Slot para guardar la nueva partida:", "Nueva Partida", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;

        try {
            int slot = Integer.parseInt(input);
            
            // Inicializar objeto de juego por defecto
            Civilization nuevaCiv = new Civilization();
            nuevaCiv.food = 10000;
            nuevaCiv.wood = 10000;
            nuevaCiv.iron = 5000;
            nuevaCiv.mana = 0;
            
            // Forzar la inicialización limpia de las 9 listas de su ejército
         // Especificamos que es una lista que contiene listas de unidades militares
            System.out.println("hola1");
            nuevaCiv.updateArmy(new ArrayList<MilitaryUnit>());
            System.out.println("Hola2");

            // Registrar inmediatamente en Base de Datos
            if (gameDAO.saveGame(slot, nuevaCiv)) {
            	System.out.println("Gola3");
            	arrancarCicloDeJuego(slot, nuevaCiv);
            } else {
                JOptionPane.showMessageDialog(this, "Error crítico: No se pudo escribir en la Base de Datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error de formato", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void gestionarCargarPartida() {
        String input = JOptionPane.showInputDialog(this, "Introduce el número de Slot que deseas cargar:", "Cargar Partida", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;

        try {
            int slot = Integer.parseInt(input);
            Civilization partidaCargada = gameDAO.loadGame(slot);

            if (partidaCargada != null) {
            	arrancarCicloDeJuego(slot, partidaCargada);            
            } else {
                JOptionPane.showMessageDialog(this, "No existe ninguna partida guardada en el Slot " + slot, "Slot vacío", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error de formato", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void arrancarCicloDeJuego(int saveSlot, Civilization civilization) {
        Timer timer = new Timer();
        
        // Traspasamos aquí la lógica de tareas temporales en segundo plano
        TimerTask task = new TimerTask() {
            int minutosTranscurridos = 0;
            int numeroBatalla = civilization.getBattles() + 1;

            public void run() {
            	System.out.println("Gola");
                minutosTranscurridos++;
                civilization.generarRecursos();
                System.out.println("Gola3");

                if (minutosTranscurridos % 3 == 0) {
                    System.out.println("\n¡ATENCIÓN! Un ejército enemigo se aproxima...");
                    ArrayList<MilitaryUnit> enemyArmy = Main.createEnemyArmy(numeroBatalla);
                    ArrayList<MilitaryUnit> myArmy = civilization.getArmy();

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

                    // Sumar batalla e incrementar el contador
                    civilization.setBattles(numeroBatalla);
                    
                    // Usamos la variable saveSlot para guardar en la ranura correcta
                    gameDAO.saveGame(saveSlot, civilization);

                    // Enviar la animación a la ventana activa
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        if (window instanceof MainMenuGUI && window.isShowing()) {
                            MainMenuGUI gui = (MainMenuGUI) window;
                            SwingUtilities.invokeLater(() -> {
                                gui.showBattleAnim(aliadosInicio, enemigosInicio, aliadosFin, enemigosFin, report);
                            });
                        }
                    }
                    numeroBatalla++;
                }
            }
        };
        
        timer.schedule(task, 60000, 60000);

        MainMenuGUI juegoGUI = new MainMenuGUI(civilization, timer);
        juegoGUI.setVisible(true);
        this.dispose();
    }
}