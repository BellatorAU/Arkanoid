import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class Arkanoid extends JPanel {
    public static final int WIDTH = 410;
    public static final int HEIGHT = 450;
    public static int default_speed = 7;
    public int speed = default_speed;
    public static boolean paused = false;
    public static boolean start_game = true;
    public static long time_counter = 0;
    public static int oldballxa = 0;
    public static int oldballya = 0;
    BufferedImage img = null;

    public Arkanoid() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.GRAY);
        setImg();
    }

    Ball ball = new Ball(this);
    Bar bar = new Bar(this);
    Bricks brick = new Bricks(this);
    ListenersHandler listeners = new ListenersHandler(this);
    Text text = new Text(this);
    CreateLevel creator = new CreateLevel(this);

    private void move() {
        ball.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 26, 410, 450, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        bar.paint(g2d);
        brick.paint(g2d);
        text.paint(g2d);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public void gameWin() {
        JOptionPane.showMessageDialog(this, "You won!", "Win", JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

    public void setImg()
    {
        try
        {
            img = ImageIO.read(new File("back.jpg"));
        }
        catch (IOException e) {}
    }

    public static void startGame(Arkanoid game) {
        if (start_game) {
            int xDirection = (int) Math.floor(Math.random()*2+1);
            game.ball.ya = -1;
            if (xDirection == 1) {
                game.ball.xa = 1;
            }
            else if (xDirection == 2) {
                game.ball.xa = -1;
            }
            start_game = false;
            game.text.start_label.setText("");
        }
        else {
            if (!paused) {
                oldballxa = game.ball.xa;
                oldballya = game.ball.ya;
                game.ball.ya = 0;
                game.ball.xa = 0;
                game.text.start_label.setText("Game Paused");
                game.text.start_label.setForeground(Color.RED);
                paused = true;
            }
            else {
                game.ball.xa = oldballxa;
                game.ball.ya = oldballya;
                game.text.start_label.setText("");
                game.text.start_label.setForeground(Color.GREEN);
                paused = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Arkanoid");
        Arkanoid game = new Arkanoid();
        frame.getContentPane().add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable( false );
        while (true) {
            System.out.println(paused);
            if (!paused && !start_game) {
                game.move();
                game.repaint();
                time_counter++;
                if (time_counter%100 == 0) {
                    if ((time_counter/100)%15 == 0) {
                        for (int i = 0; i < game.brick.bricks.size(); i++) {
                            game.brick.bricks.get(i).y += 10;
                        }
                    }
                }
                Thread.sleep(game.speed);
            }
        }
    }
}