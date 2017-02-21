import java.awt.Color;



public class CreateLevel {
    public int row_num = 4;

    private Arkanoid game;

    public CreateLevel(Arkanoid game) {
        this.game = game;
        create();
    }

    void create() {
        for (int i = 0; i <= row_num; i++) {
            for (int j = 0; j <= 17; j++) {
                Bricks.Brick brick = new Bricks.Brick();
                brick.x += j * 20;
                brick.y = i * 20 + 80;
                switch (i) {
                    case 0:
                        brick.color = Color.LIGHT_GRAY;
                        brick.hits = 2;
                        break;
                    case 2:
                        brick.color = Color.YELLOW;
                        brick.hits = 1;
                        break;
                    case 4:
                        brick.color = Color.GREEN;
                        brick.hits = 0;
                        break;
                }
                if (j % 2 != 0 && i % 2 == 0) {
                    game.brick.bricks.add(brick);
                }
            }
            game.brick.brickRows.add(game.brick.bricks);
        }
    }
}