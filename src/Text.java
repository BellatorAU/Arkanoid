import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Text {
    public static int menu_bar_height = 25;
    JLabel start_label = new JLabel("Click To Start", SwingConstants.CENTER);
    JLabel lives_text_label = new JLabel("|| Lives: ");
    JLabel lives_label = new JLabel("");
    JLabel title_label = new JLabel("ARKANOID", SwingConstants.CENTER);
    Font lfont = new Font("courier", Font.PLAIN, 13);
    Font rwdfont = new Font("courier", Font.BOLD, 17);
    Font titlefont = new Font("courier", Font.BOLD, 13);

    private Arkanoid game;

    public Text(Arkanoid game) {
        this.game = game;
        makeStartLabel();
        makeLivesLabel();
        makeTitleLabel();
    }

    void makeStartLabel() {
        start_label.setVisible(true);
        start_label.setBounds(0, 255, Arkanoid.WIDTH, 100);
        start_label.setFont(lfont);
        start_label.setForeground(Color.GREEN);
        game.add(start_label);
    }
    void makeLivesLabel() {
        lives_text_label.setVisible(true); lives_label.setVisible(true);
        lives_text_label.setBounds(Arkanoid.WIDTH-103, -35, Arkanoid.WIDTH, 100);
        lives_label.setBounds(Arkanoid.WIDTH-22, -35, Arkanoid.WIDTH, 100);
        lives_text_label.setFont(lfont); lives_label.setFont(lfont);
        lives_text_label.setForeground(Color.BLUE);
        lives_label.setForeground(Color.GREEN);
        game.add(lives_text_label);
        lives_label.setText(""+game.bar.lives);
        game.add(lives_label);
    }

    void makeTitleLabel() {
        title_label.setVisible(true);
        title_label.setBounds(0, -35, Arkanoid.WIDTH, 100);
        title_label.setFont(titlefont);
        title_label.setForeground(Color.WHITE);
        game.add(title_label);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 1, Arkanoid.WIDTH, 2);
        g.fillRect(0, menu_bar_height, Arkanoid.WIDTH, 2);
        start_label.paint(g);
    }
}