// imports
import java.awt.Graphics;
import java.awt.Color;

public class Rock {

    // instance variables
    private int x, y;
    private int dim;
    private Color c;

    // constructors
    public Rock(int rx, int ry, int rdim, Color rc) {
        x = rx;
        y = ry;
        dim = rdim;
        c = rc;
    }

    // behaviors
    public void draw(Graphics p) {
        p.setColor(c);
        p.fillRect(x, y, dim, dim);
    }

    public void checkForInteraction(Player p) {
        // top
        if (p.x == x && p.y == (y - dim)) {
            p.startProgressBar = true;
            p.mStone = true;
        // left
        } else if (p.x == (x - dim) && p.y == y) {
            p.startProgressBar = true;
            p.mStone = true;
        // bottom
        } else if (p.x == x && p.y == (y + dim)) {
            p.startProgressBar = true;
            p.mStone = true;
        // right
        } else if (p.x == (x + dim) && p.y == y) {
            p.startProgressBar = true;
            p.mStone = true;
        }
    }

    // animal collision
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