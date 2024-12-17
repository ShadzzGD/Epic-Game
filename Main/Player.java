// imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

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
    public boolean hasDagger;
    public boolean hasAxe;
    public boolean hasPickaxe;

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
        hasDagger = false;
        hasAxe = false;
        hasPickaxe = false;

        // stats
        food = 40;
        energy = 50;
        hungerLossVar = 0;

        // controlling variables
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

    // inventory
    public void drawInventory(Graphics p, int selectorX, int selectorY, int screenW, Color darkerMaroon, Font smallerText, Font biggerText) {
        p.fillRect(0, 375, screenW, 100);
        p.setColor(Color.WHITE);
        p.setFont(biggerText);
        p.drawString("Wood: " + Integer.toString(wood), 0, 400);
        p.drawString("Stone: " + Integer.toString(stone), 0, 425);
        p.drawString("Meat: " + Integer.toString(meat), 0, 450);
        p.drawString(Integer.toString(food) + " :Hunger", 375, 400);
        p.drawString(Integer.toString(energy) + " :Energy", 375, 425);
        // draw hot bar
        p.setColor(darkerMaroon);
        p.fillRect(100, 380, 85, 75);
        p.fillRect(190, 380, 85, 75);
        p.fillRect(280, 380, 85, 75);
        // draw selector
        p.setColor(Color.WHITE);
        p.drawRect(selectorX, selectorY, 85, 75);
        // draw items
        p.setFont(smallerText);
        p.setColor(Color.RED);
        p.drawString("Dagger", 115, 420);
        p.drawString("Axe", 220, 420);
        p.drawString("Pickaxe", 295, 420);
    }

    // info boxes
    public void drawInfoBox(Graphics p, int sel) {
        if (sel == 1) {

        }
    }

    // drawing to screen
    public void draw(Graphics p) {
        p.setColor(c);
        p.fillRect(x, y, dim, dim);
    }
}