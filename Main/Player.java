// imports
import java.awt.Color;
import java.awt.Graphics;

public class Player {

    // instance variables
    public int x, y;
    public int vel;
    public int dim;
    public Color c;

    public int wood;
    public int stone;
    public int meat;
    public int food;
    public int energy;
    public int hungerLossVar;

    public String dir;

    public boolean startProgressBar;
    public boolean mWood;
    public boolean mStone;
    public boolean hurtAnimal;
    public boolean eating;
    public boolean alive;

    // constructors
    public Player(int px, int py, int ps, int pdim, Color pc) {
        x = px;
        y = py;
        vel = ps;
        dim = pdim;
        c = pc;

        // inv
        wood = 0;
        stone = 0;
        meat = 0;

        // stats
        food = 40;
        energy = 50;
        hungerLossVar = 0;

        startProgressBar = false;
        mWood = false;
        mStone = false;
        hurtAnimal = false;
        eating = false;
        alive = true;
    }

    // behaviors
    // movement
    public void down() {
        y += vel;
        dir = "down";
    }
    public void up() {
        y -= vel;
        dir = "up";
    }
    public void left() {
        x -= vel;
        dir = "left";
    } 
    public void right() {
        x += vel;
        dir = "right";
    }

    // lose hunger
    public void naturalDrain() {
        if (hungerLossVar >= 150) {
            food -= 1;
            hungerLossVar = 0;
        }
        hungerLossVar += 1;
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

    // progress bar for actions
    public void drawProgressBar(Graphics p) {
        p.setColor(Color.RED);
        p.fillRect((x - 10), (y - 15), ((dim * 2) - 6), 10);
    }
    // extending the overlapping bar
    public void drawSecondOne(Graphics p, int w) {
        p.setColor(Color.GREEN);
        p.fillRect((x - 10), (y - 15), w, 10);
    }

    // DIE!!!!
    public void death(Graphics p, int sWidth) {
        if (food <= 0) {
            p.setColor(Color.RED);
            p.drawString("You Died!", (sWidth / 2) - (p.getFontMetrics().stringWidth("You Died!") / 2), 200);
            alive = false;
        }
    }

    // drawing to screen
    public void draw(Graphics p) {
        p.setColor(c);
        p.fillRect(x, y, dim, dim);
    }
}