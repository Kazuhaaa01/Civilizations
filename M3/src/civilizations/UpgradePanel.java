package civilizations;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UpgradePanel extends JPanel implements ActionListener {

    Civilization civ;

    JButton btnUpgradeAttack   = new JButton("Upgrade Attack");
    JButton btnUpgradeDefense  = new JButton("Upgrade Defense");

    JLabel lblAttackLevel  = new JLabel();
    JLabel lblDefenseLevel = new JLabel();

    JLabel lblAttackCost  = new JLabel();
    JLabel lblDefenseCost = new JLabel();

    static final Color COLOR_IRON = new Color(180, 180, 255);

    public UpgradePanel(Civilization civ) {
        this.civ = civ;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(70, 70, 70));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── ATTACK ──
        JPanel panelAttack = new JPanel();
        panelAttack.setLayout(new BoxLayout(panelAttack, BoxLayout.Y_AXIS));
        panelAttack.setBackground(new Color(60, 60, 60));
        panelAttack.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleAttack = new JLabel("⚔ Attack Technology");
        titleAttack.setForeground(Color.WHITE);
        titleAttack.setFont(new Font("Arial", Font.BOLD, 13));

        lblAttackLevel.setForeground(Color.LIGHT_GRAY);
        lblAttackLevel.setFont(new Font("Arial", Font.PLAIN, 12));

        lblAttackCost.setForeground(COLOR_IRON);
        lblAttackCost.setFont(new Font("Arial", Font.BOLD, 12));

        btnUpgradeAttack.addActionListener(this);
        btnUpgradeAttack.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelAttack.add(titleAttack);
        panelAttack.add(Box.createVerticalStrut(5));
        panelAttack.add(lblAttackLevel);
        panelAttack.add(lblAttackCost);
        panelAttack.add(Box.createVerticalStrut(8));
        panelAttack.add(btnUpgradeAttack);

        // ── DEFENSE ──
        JPanel panelDefense = new JPanel();
        panelDefense.setLayout(new BoxLayout(panelDefense, BoxLayout.Y_AXIS));
        panelDefense.setBackground(new Color(60, 60, 60));
        panelDefense.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleDefense = new JLabel("🛡 Defense Technology");
        titleDefense.setForeground(Color.WHITE);
        titleDefense.setFont(new Font("Arial", Font.BOLD, 13));

        lblDefenseLevel.setForeground(Color.LIGHT_GRAY);
        lblDefenseLevel.setFont(new Font("Arial", Font.PLAIN, 12));

        lblDefenseCost.setForeground(COLOR_IRON);
        lblDefenseCost.setFont(new Font("Arial", Font.BOLD, 12));

        btnUpgradeDefense.addActionListener(this);
        btnUpgradeDefense.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelDefense.add(titleDefense);
        panelDefense.add(Box.createVerticalStrut(5));
        panelDefense.add(lblDefenseLevel);
        panelDefense.add(lblDefenseCost);
        panelDefense.add(Box.createVerticalStrut(8));
        panelDefense.add(btnUpgradeDefense);

        add(panelAttack);
        add(Box.createVerticalStrut(15));
        add(panelDefense);

        actualizarInfo();
    }

    private void actualizarInfo() {
        lblAttackLevel.setText("Level: " + civ.technologyAtack);
        lblDefenseLevel.setText("Level: " + civ.technologyDefense);
        lblAttackCost.setText("Cost: " + civ.upgradeAttackTechnologyIronCost + " iron");
        lblDefenseCost.setText("Cost: " + civ.upgradeDefenseTechnologyIronCost + " iron");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object font = event.getSource();

        if (font == btnUpgradeAttack) {
            try {
                civ.upgradeTechnologyAttack();
                JOptionPane.showMessageDialog(this, "Attack Technology upgraded to level " + civ.technologyAtack + "!", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (font == btnUpgradeDefense) {
            try {
                civ.upgradeTechnologyDefense();
                JOptionPane.showMessageDialog(this, "Defense Technology upgraded to level " + civ.technologyDefense + "!", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        actualizarInfo(); // actualiza nivel y coste tras cada upgrade
    }
}