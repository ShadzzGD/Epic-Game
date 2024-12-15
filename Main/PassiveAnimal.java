// imports
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PassiveAnimal {

    // instance variables
    int x, y;
    int dim;
    Color c;
    int movement;
    int randomNum;
    int anotherRandomNum;
    String dir;
    boolean alive;
    int anotherWidthVar;
    int respawnTimer;

    // constructors
    public PassiveAnimal(int pax, int pay, int padim, Color pac) {
        x = pax;
        y = pay;
        dim = padim;
        c = pac;
        alive = true;
        anotherWidthVar = ((dim * 2) - 5);
        respawnTimer = 0;

        movement = 990000;
    }

    // behaviors 
    public void movementAI(Random rand) {
        randomNum = rand.nextInt(1000000);
        if (randomNum > movement) {
            anotherRandomNum = rand.nextInt(5);
            // up
            switch (anotherRandomNum) {
                case 1 -> {
                    y -= dim;
                    dir = "up";
                    // left
                }
                case 2 -> {
                    x -= dim;
                    dir = "left";
                    // down
                }
                case 3 -> {
                    y += dim;
                    dir = "down";
                    // right
                }
                case 4 -> {
                    x += dim;
                    dir = "right";
                }
                default -> {
                }
            }
        }
    }

    // check if the player is close to the animal
    public void checkForInteraction(Player p) {
        // top
        if (p.x == x && p.y == (y - dim)) {
            p.startProgressBar = true;
            p.hurtAnimal = true;
        // left
        } else if (p.x == (x - dim) && p.y == y) {
            p.startProgressBar = true;
            p.hurtAnimal = true;
        // bottom
        } else if (p.x == x && p.y == (y + dim)) {
            p.startProgressBar = true;
            p.hurtAnimal = true;
        // right
        } else if (p.x == (x + dim) && p.y == y) {
            p.startProgressBar = true;
            p.hurtAnimal = true;
        }
    }

    // border collision
    public void barrierCheck(int sWidth, int sHeight) {
        // x
        if (x < 0) {
            x += dim;
        } else if (x > (sWidth - (dim * 2))) {
            x -= dim;
        // y
        } else if (y < 0) {
            y += dim;
        } else if (y > (sHeight - (dim * 6))) {
            y -= dim;
        }
    }

    public void collision(Player p) {
        // player collision
        if (alive == true) {
            if (p.x == x && p.y == y) {
                if (null != p.dir) switch (p.dir) {
                    case "left" -> p.x += p.dim;
                    case "right" -> p.x -= p.dim;
                    case "up" -> p.y += p.dim;
                    case "down" -> p.y -= p.dim;
                    default -> {
                    }
                }
            }
        }
    }

    // health bar
    public void drawProgressBar(Graphics p) {
        p.setColor(Color.RED);
        p.fillRect((x - 10), (y - 15), ((dim * 2) - 5), 10);
    }
    // health bar but better
    public void drawSecondOne(Graphics p) {
        p.setColor(Color.GREEN);
        p.fillRect((x - 10), (y - 15), anotherWidthVar, 10);
    }

    public void draw(Graphics p) {
        p.setColor(c);
        p.fillRect(x, y, dim, dim);
    }
}