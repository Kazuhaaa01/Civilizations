package civilizations;

import java.awt.*;
import javax.swing.*;

public class BattleAnimPanel extends JPanel {

    private static final int[] ALLY_POSITIONS_X  = {210, 230, 195, 250, 215, 235, 200, 245, 220, 205};
    private static final int[] ALLY_POSITIONS_Y  = {280, 300, 310, 290, 330, 320, 295, 335, 350, 270};

    private static final int[] ENEMY_POSITIONS_X = {380, 400, 365, 420, 390, 410, 375, 430, 405, 360};
    private static final int[] ENEMY_POSITIONS_Y = {260, 275, 285, 265, 295, 305, 250, 290, 310, 280};

    private int aliadosPuntos;
    private int enemigosPuntos;

    private final int aliadosPuntosInicio;
    private final int enemigosPuntosInicio;
    private final int aliadosPuntosFin;
    private final int enemigosPuntosFin;

    private float[] aliadosAlpha;
    private float[] enemigosAlpha;

    private int tick = 0;
    private final int totalTicks = 50;
    private javax.swing.Timer animTimer;
    private Runnable onFinished;

    private boolean mostrarLabel = true;
    private int labelTick = 0;

    public BattleAnimPanel(int aliadosInicio, int enemigosInicio,
                           int aliadosFin,   int enemigosFin,
                           Runnable onFinished) {

        this.onFinished = onFinished;
        setOpaque(false);

        aliadosPuntosInicio  = escalar(aliadosInicio,  Math.max(aliadosInicio, enemigosInicio));
        enemigosPuntosInicio = escalar(enemigosInicio, Math.max(aliadosInicio, enemigosInicio));
        aliadosPuntosFin     = Math.max(0, escalar(aliadosFin,  Math.max(aliadosInicio, enemigosInicio)));
        enemigosPuntosFin    = Math.max(0, escalar(enemigosFin, Math.max(aliadosInicio, enemigosInicio)));

        aliadosPuntos  = aliadosPuntosInicio;
        enemigosPuntos = enemigosPuntosInicio;

        aliadosAlpha  = new float[10];
        enemigosAlpha = new float[10];
        for (int i = 0; i < 10; i++) {
            aliadosAlpha[i]  = (i < aliadosPuntos)  ? 1.0f : 0.0f;
            enemigosAlpha[i] = (i < enemigosPuntos) ? 1.0f : 0.0f;
        }

        boolean aliadosGanan = aliadosFin > enemigosFin;

        animTimer = new javax.swing.Timer(100, e -> {
            tick++;
            labelTick++;
            if (labelTick % 5 == 0) mostrarLabel = !mostrarLabel;

            float progreso = (float) tick / totalTicks;

            int targetAliados, targetEnemigos;

            if (aliadosGanan) {
                targetEnemigos = (int) Math.max(enemigosPuntosFin,
                    enemigosPuntosInicio - (enemigosPuntosInicio - enemigosPuntosFin) * Math.pow(progreso, 0.5));
                targetAliados  = (int) Math.max(aliadosPuntosFin,
                    aliadosPuntosInicio - (aliadosPuntosInicio - aliadosPuntosFin) * Math.pow(progreso, 2.0));
            } else {
                targetAliados  = (int) Math.max(aliadosPuntosFin,
                    aliadosPuntosInicio - (aliadosPuntosInicio - aliadosPuntosFin) * Math.pow(progreso, 0.5));
                targetEnemigos = (int) Math.max(enemigosPuntosFin,
                    enemigosPuntosInicio - (enemigosPuntosInicio - enemigosPuntosFin) * Math.pow(progreso, 2.0));
            }

            fadePoints(aliadosAlpha,  aliadosPuntos,  targetAliados);
            fadePoints(enemigosAlpha, enemigosPuntos, targetEnemigos);
            aliadosPuntos  = targetAliados;
            enemigosPuntos = targetEnemigos;

            repaint();

            if (tick >= totalTicks) {
                animTimer.stop();
                if (onFinished != null) {
                    SwingUtilities.invokeLater(onFinished);
                }
            }
        });
    }

    private void fadePoints(float[] alphas, int actual, int target) {
        if (actual <= target) return;
        for (int i = actual - 1; i >= target; i--) {
            if (i >= 0 && i < 10) {
                alphas[i] = Math.max(0f, alphas[i] - 0.15f);
            }
        }
    }

    private int escalar(int valor, int maximo) {
        if (maximo == 0) return 0;
        return Math.max(1, Math.min(10, (int) Math.round(valor * 10.0 / maximo)));
    }

    public void startAnim() {
        animTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int radio = 14;

        // ── Aliados (azul) ──
        for (int i = 0; i < 10; i++) {
            float a = aliadosAlpha[i];
            if (a <= 0f) continue;

            int x = ALLY_POSITIONS_X[i] - radio / 2;
            int y = ALLY_POSITIONS_Y[i] - radio / 2;

            g2.setColor(new Color(60, 140, 255, (int)(220 * a)));
            g2.fillOval(x, y, radio, radio);
        }

        // ── Enemigos (rojo) ──
        for (int i = 0; i < 10; i++) {
            float a = enemigosAlpha[i];
            if (a <= 0f) continue;

            int x = ENEMY_POSITIONS_X[i] - radio / 2;
            int y = ENEMY_POSITIONS_Y[i] - radio / 2;

            g2.setColor(new Color(220, 50, 50, (int)(220 * a)));
            g2.fillOval(x, y, radio, radio);
        }

        // ── Label "⚔ BATTLE!" parpadeante ──
        if (mostrarLabel) {
            String txt = "⚔ BATTLE!";
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics fm = g2.getFontMetrics();
            int tw = fm.stringWidth(txt);
            int tx = 300 - tw / 2;
            int ty = 310;

            g2.setColor(new Color(0, 0, 0, 160));
            g2.fillRoundRect(tx - 8, ty - fm.getAscent() - 2, tw + 16, fm.getHeight() + 4, 8, 8);

            g2.setColor(new Color(255, 80, 80));
            g2.drawString(txt, tx, ty);
        }
    }
}