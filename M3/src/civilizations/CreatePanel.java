package civilizations;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreatePanel extends JPanel implements ActionListener {

    Civilization civ;

    JButton btnAttack    = new JButton("Attack Units");
    JButton btnDefense   = new JButton("Defense Units");
    JButton btnSpecial   = new JButton("Special Units");
    JButton btnBuildings = new JButton("Buildings");

    JPanel panelSub;

    // Colores de recursos (los mismos que en MainMenuGUI)
    static final Color COLOR_FOOD = new Color(255, 200, 50);
    static final Color COLOR_WOOD = new Color(150, 255, 100);
    static final Color COLOR_IRON = new Color(180, 180, 255);
    static final Color COLOR_MANA = new Color(100, 200, 255);

    public CreatePanel(Civilization civ) {
        this.civ = civ;
        setLayout(new BorderLayout());
        setBackground(new Color(70, 70, 70));

        JPanel panelButtons = new JPanel(new GridLayout(4, 1, 5, 5));
        panelButtons.setBackground(new Color(70, 70, 70));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAttack.addActionListener(this);
        btnDefense.addActionListener(this);
        btnSpecial.addActionListener(this);
        btnBuildings.addActionListener(this);

        panelButtons.add(btnAttack);
        panelButtons.add(btnDefense);
        panelButtons.add(btnSpecial);
        panelButtons.add(btnBuildings);

        add(panelButtons, BorderLayout.NORTH);

        panelSub = new JPanel();
        panelSub.setBackground(new Color(60, 60, 60));
        panelSub.setLayout(new BoxLayout(panelSub, BoxLayout.Y_AXIS));
        add(panelSub, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object font = event.getSource();

        if (font == btnAttack) {
            // {nombre, food, wood, iron, mana}
            showUnits(new String[][]{
                {"Swordsman",      "8000", "3000",  "50",    "0"},
                {"Spearman",       "5000", "6500",  "50",    "0"},
                {"Crossbow",       "0",    "45000", "7000",  "0"},
                {"Cannon",         "0",    "30000", "15000", "0"}
            });
        } else if (font == btnDefense) {
            showUnits(new String[][]{
                {"Arrow Tower",     "0", "2000",  "0",    "0"},
                {"Catapult",        "0", "4000",  "500",  "0"},
                {"Rocket Launcher", "0", "50000", "5000", "0"}
            });
        } else if (font == btnSpecial) {
            showUnits(new String[][]{
                {"Magician", "12000", "2000", "500", "5000"},
                {"Priest",   "15000", "0",    "0",   "15000"}
            });
        } else if (font == btnBuildings) {
            showBuildings();
        }
    }

    // Construye una fila con nombre + costes en color + campo cantidad + botón
    private void showUnits(String[][] units) {
        panelSub.removeAll();

        for (String[] unit : units) {
            String name  = unit[0];
            int food     = Integer.parseInt(unit[1]);
            int wood     = Integer.parseInt(unit[2]);
            int iron     = Integer.parseInt(unit[3]);
            int mana     = Integer.parseInt(unit[4]);

            // Panel de la fila completa (nombre + costes + cantidad + botón)
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
            row.setBackground(new Color(60, 60, 60));
            row.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

            // Línea 1: nombre + cantidad + botón
            JPanel lineTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
            lineTop.setBackground(new Color(60, 60, 60));

            JLabel lbl = new JLabel(name);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            lbl.setPreferredSize(new Dimension(110, 20));

            JTextField txtAmount = new JTextField("1", 3);

            JButton btnCreate = new JButton("Create");
            btnCreate.addActionListener(e -> {
                try {
                    int amount = Integer.parseInt(txtAmount.getText());
                    createUnit(name, amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            lineTop.add(lbl);
            lineTop.add(txtAmount);
            lineTop.add(btnCreate);

            // Línea 2: costes en color (solo los que sean > 0)
            JPanel lineCosts = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
            lineCosts.setBackground(new Color(60, 60, 60));

            if (food > 0) lineCosts.add(costLabel(food + "", COLOR_FOOD));
            if (wood > 0) lineCosts.add(costLabel(wood + "", COLOR_WOOD));
            if (iron > 0) lineCosts.add(costLabel(iron + "", COLOR_IRON));
            if (mana > 0) lineCosts.add(costLabel(mana + "", COLOR_MANA));

            row.add(lineTop);
            row.add(lineCosts);
            panelSub.add(row);
        }

        panelSub.revalidate();
        panelSub.repaint();
    }

    private void showBuildings() {
        panelSub.removeAll();

        // {nombre, food, wood, iron}
        String[][] buildings = {
            {"Farm",        "5000", "10000", "12000"},
            {"Carpentry",   "5000", "10000", "12000"},
            {"Smithy",      "5000", "10000", "12000"},
            {"Magic Tower", "5000", "10000", "12000"},
            {"Church",      "5000", "10000", "12000"}
        };

        for (String[] building : buildings) {
            String name = building[0];
            int food    = Integer.parseInt(building[1]);
            int wood    = Integer.parseInt(building[2]);
            int iron    = Integer.parseInt(building[3]);

            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
            row.setBackground(new Color(60, 60, 60));
            row.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

            JPanel lineTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
            lineTop.setBackground(new Color(60, 60, 60));

            JLabel lbl = new JLabel(name);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            lbl.setPreferredSize(new Dimension(110, 20));

            JButton btnBuild = new JButton("Build");
            btnBuild.addActionListener(e -> createBuilding(name));

            lineTop.add(lbl);
            lineTop.add(btnBuild);

            JPanel lineCosts = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
            lineCosts.setBackground(new Color(60, 60, 60));
            lineCosts.add(costLabel(food + "", COLOR_FOOD));
            lineCosts.add(costLabel(wood + "", COLOR_WOOD));
            lineCosts.add(costLabel(iron + "", COLOR_IRON));

            row.add(lineTop);
            row.add(lineCosts);
            panelSub.add(row);
        }

        panelSub.revalidate();
        panelSub.repaint();
    }

    // Crea un JLabel con el coste en el color del recurso correspondiente
    private JLabel costLabel(String text, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(color);
        lbl.setFont(new Font("Arial", Font.BOLD, 11));
        return lbl;
    }

    private void createUnit(String unit, int amount) {
        try {
            switch (unit) {
                case "Swordsman":       civ.newSwordsman(amount);      break;
                case "Spearman":        civ.newSpearman(amount);       break;
                case "Crossbow":        civ.newCrossbow(amount);       break;
                case "Cannon":          civ.newCannon(amount);         break;
                case "Arrow Tower":     civ.newArrowTower(amount);     break;
                case "Catapult":        civ.newCatapult(amount);       break;
                case "Rocket Launcher": civ.newRocketLauncher(amount); break;
                case "Magician":        civ.newMagician(amount);       break;
                case "Priest":          civ.newPriest(amount);         break;
            }
            JOptionPane.showMessageDialog(this, amount + " " + unit + " created!", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createBuilding(String building) {
        try {
            switch (building) {
                case "Farm":        civ.newFarm();       break;
                case "Carpentry":   civ.newCarpentry();  break;
                case "Smithy":      civ.newSmithy();     break;
                case "Magic Tower": civ.newMagicTower(); break;
                case "Church":      civ.newChurch();     break;
            }
            JOptionPane.showMessageDialog(this, building + " built!", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}