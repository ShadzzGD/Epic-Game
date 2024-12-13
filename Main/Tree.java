// imports
import java.awt.Graphics;
import java.awt.Color;

public class Tree {

    // instance variables
    private int x, y;
    private int dim;
    private Color c;

    // constructors
    public Tree(int tx, int ty, int tdim, Color tc) {
        x = tx;
        y = ty;
        dim = tdim;
        c = tc;
    }

    // behaviors
    public void draw(Graphics p) {
        p.setColor(c);
        p.fillRect(x, y, dim, dim);
    }

    // check for proximity with player
    public void checkForInteraction(Player p) {
        // top
        if (p.x == x && p.y == (y - dim)) {
            p.startProgressBar = true;
            p.mWood = true;
        // left
        } else if (p.x == (x - dim) && p.y == y) {
            p.startProgressBar = true;
            p.mWood = true;
        // bottom
        } else if (p.x == x && p.y == (y + dim)) {
            p.startProgressBar = true;
            p.mWood = true;
        // right
        } else if (p.x == (x + dim) && p.y == y) {
            p.startProgressBar = true;
            p.mWood = true;
        }
    }

    // aimal collision
    public void animalCollision(PassiveAnimal pa) {
        if (pa.x == x && pa.y == y) {
            if (pa.dir == "left") {
                pa.x += pa.dim;
            } else if (pa.dir == "right") {
                pa.x -= pa.dim;
            } else if (pa.dir == "up") {
                pa.y += pa.dim;
            } else if (pa.dir == "down") {
                pa.y -= pa.dim;
            }
        }
    }

    // collision
    public void collisionCheck(Player p) {
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