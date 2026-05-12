package civilizations;

import java.awt.*;
import javax.swing.*;

public class StatsPanel extends JPanel {

    Civilization civ;

    static final Color COLOR_FOOD  = new Color(255, 200, 50);
    static final Color COLOR_WOOD  = new Color(150, 255, 100);
    static final Color COLOR_IRON  = new Color(180, 180, 255);
    static final Color COLOR_MANA  = new Color(100, 200, 255);
    static final Color COLOR_TITLE = new Color(220, 220, 220);
    static final Color COLOR_VAL   = Color.WHITE;

    public StatsPanel(Civilization civ) {
        this.civ = civ;
        setLayout(new BorderLayout());
        setBackground(new Color(70, 70, 70));

        // Panel interior con el contenido
        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setBackground(new Color(70, 70, 70));
        inner.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buildContent(inner);

        // ScrollPane que envuelve el panel interior
        JScrollPane scroll = new JScrollPane(inner);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(70, 70, 70));

        add(scroll, BorderLayout.CENTER);
    }

    private void buildContent(JPanel inner) {
        // ── TECHNOLOGY ──
        inner.add(sectionTitle("⚔ Technology"));
        inner.add(rowData("Attack",  String.valueOf(civ.technologyAtack),  COLOR_VAL));
        inner.add(rowData("Defense", String.valueOf(civ.technologyDefense), COLOR_VAL));
        inner.add(spacer());

        // ── BUILDINGS ──
        inner.add(sectionTitle("🏰 Buildings"));
        inner.add(rowData("Farm",        String.valueOf(civ.farm),       COLOR_VAL));
        inner.add(rowData("Carpentry",   String.valueOf(civ.carpentry),  COLOR_VAL));
        inner.add(rowData("Smithy",      String.valueOf(civ.smithy),     COLOR_VAL));
        inner.add(rowData("Magic Tower", String.valueOf(civ.magicTower), COLOR_VAL));
        inner.add(rowData("Church",      String.valueOf(civ.church),     COLOR_VAL));
        inner.add(spacer());

        // ── ATTACK UNITS ──
        inner.add(sectionTitle("🗡 Attack Units"));
        inner.add(rowData("Swordsman", String.valueOf(civ.army[0].size()), COLOR_VAL));
        inner.add(rowData("Spearman",  String.valueOf(civ.army[1].size()), COLOR_VAL));
        inner.add(rowData("Crossbow",  String.valueOf(civ.army[2].size()), COLOR_VAL));
        inner.add(rowData("Cannon",    String.valueOf(civ.army[3].size()), COLOR_VAL));
        inner.add(spacer());

        // ── DEFENSE UNITS ──
        inner.add(sectionTitle("🛡 Defense Units"));
        inner.add(rowData("Arrow Tower",     String.valueOf(civ.army[4].size()), COLOR_VAL));
        inner.add(rowData("Catapult",        String.valueOf(civ.army[5].size()), COLOR_VAL));
        inner.add(rowData("Rocket Launcher", String.valueOf(civ.army[6].size()), COLOR_VAL));
        inner.add(spacer());

        // ── SPECIAL UNITS ──
        inner.add(sectionTitle("✨ Special Units"));
        inner.add(rowData("Magician", String.valueOf(civ.army[7].size()), COLOR_VAL));
        inner.add(rowData("Priest",   String.valueOf(civ.army[8].size()), COLOR_VAL));
        inner.add(spacer());

        // ── GENERATION PER MINUTE ──
        inner.add(sectionTitle("📈 Generation / min"));
        int foodGen = Variables.CIVILIZATION_FOOD_GENERATED + (civ.farm * Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM);
        int woodGen = Variables.CIVILIZATION_WOOD_GENERATED + (civ.carpentry * Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
        int ironGen = Variables.CIVILIZATION_IRON_GENERATED + (civ.smithy * Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY);
        int manaGen = civ.magicTower * Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
        inner.add(rowData("Food", String.valueOf(foodGen), COLOR_FOOD));
        inner.add(rowData("Wood", String.valueOf(woodGen), COLOR_WOOD));
        inner.add(rowData("Iron", String.valueOf(ironGen), COLOR_IRON));
        inner.add(rowData("Mana", String.valueOf(manaGen), COLOR_MANA));
    }

    private JLabel sectionTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(COLOR_TITLE);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setBorder(BorderFactory.createEmptyBorder(6, 0, 2, 0));
        return lbl;
    }

    private JPanel rowData(String name, String value, Color valueColor) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(70, 70, 70));
        row.setMaximumSize(new Dimension(240, 18));

        JLabel lblName = new JLabel(name);
        lblName.setForeground(new Color(180, 180, 180));
        lblName.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel lblValue = new JLabel(value);
        lblValue.setForeground(valueColor);
        lblValue.setFont(new Font("Arial", Font.BOLD, 11));

        row.add(lblName,  BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);
        return row;
    }

    private Box.Filler spacer() {
        return (Box.Filler) Box.createVerticalStrut(6);
    }
}