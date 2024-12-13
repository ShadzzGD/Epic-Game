// imports
import java.awt.Graphics;
import java.awt.Color;
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
            if (anotherRandomNum == 1) {
                y -= dim;
                dir = "up";
            // left
            } else if (anotherRandomNum == 2) {
                x -= dim;
                dir = "left";
            // down
            } else if (anotherRandomNum == 3) {
                y += dim;
                dir = "down";
            // right
            } else if (anotherRandomNum == 4) {
                x += dim;
                dir = "right";
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
                if (p.dir == "left") {
                    p.x += p.dim;
                } else if (p.dir == "right") {
                    p.x -= p.dim;
                } else if (p.dir == "up") {
                    p.y += p.dim;
                } else if (p.dir == "down") {
                    p.y -= p.dim;
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