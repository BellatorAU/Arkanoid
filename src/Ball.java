import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
    public static int DIAMETER = 10;
    public static int default_x = 205;
    public static int default_y = 350;
    int x = default_x;
    int y = default_y;
    int xa = 0;
    int ya = 0;
    int brick;
    int ball_top_position = 0;
    int ball_bot_position = 0;
    int ball_right_position = 0;
    int ball_left_position = 0;
    int brick_top_position = 0;
    int brick_bot_position = 0;
    int brick_right_position = 0;
    int brick_left_position = 0;

    private Arkanoid game;

    public Ball(Arkanoid game) {
        this.game = game;
    }

    void move() {
        if (x + xa <= 0)
            xa *= -1;
        else if (x + xa >= game.getWidth() - DIAMETER)
            xa = -xa;
        else if (y + ya <= Text.menu_bar_height)
            ya = 1;
        else if (y + ya >= game.getHeight() - DIAMETER) {
            if (game.bar.lives == 0)
                game.gameOver();
            else if (game.bar.lives > 0) {
                Bar.looseLive(game);
            }
        }
        else if (collision()){
            ya = -1;
            y = game.bar.getTopY() - DIAMETER + 10;
        }
        else if (brickCollision()){
            ball_top_position = y;
            ball_bot_position = y+DIAMETER;
            ball_right_position = x+DIAMETER+14;
            ball_left_position = x;
            brick_top_position = game.brick.bricks.get(brick).y+1;
            brick_bot_position = game.brick.bricks.get(brick).y+Bricks.Brick.height-1;
            brick_right_position = game.brick.bricks.get(brick).x+14;
            brick_left_position = game.brick.bricks.get(brick).x+Bricks.Brick.width;
            if ((ball_bot_position == brick_top_position || ball_top_position == brick_bot_position)
                    && ball_right_position != brick_left_position && ball_left_position != brick_right_position) {
                if (ya == 1)
                    ya = -1;
                else if (ya == -1)
                    ya = 1;
            }
            else {
                if (xa > 0)
                    xa *= -1;
                else if (xa < 0)
                    xa *= -1;
            }
            if (game.brick.bricks.get(brick).hits == 0) {
                game.brick.bricks.remove(brick);
            }
            else {
                game.brick.updateHits(brick);
            }

            if (game.brick.bricks.size() == 0) {
                game.gameWin();
            }
        }
        x = x + xa;
        y = y + ya;
    }

    private boolean collision() {
        if (game.bar.getBounds().intersects(getBounds())) {
            return true;
        }
        else if (game.bar.getLeftBounds().intersects(getBounds())) {
            if (xa > 0) {
                xa *= -1;
                if (x*xa < 0 && xa+1 != 0)
                    xa++;
            }
            else if (xa < 0) {
                if (x*xa < 0 && xa-1 != 0)
                    xa--;
            }
            return true;
        }
        else if (game.bar.getRightBounds().intersects(getBounds())) {
            if (xa < 0) {
                xa *= -1;
                if (x*xa > 0 && xa-1 != 0)
                    xa--;
            }
            else if (xa > 0) {
                if (x*xa > 0 && xa+1 != 0)
                    xa++;
            }
            return true;
        }
        else {
            return false;
        }
    }

    private boolean brickCollision() {
        for (int i = 0; i < game.brick.bricks.size(); i++) {
            if (game.brick.bricks.get(i).getBounds().intersects(getBounds())) {
                brick = i;
                return game.brick.bricks.get(i).getBounds().intersects(getBounds());
            }
        }
        return false;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}