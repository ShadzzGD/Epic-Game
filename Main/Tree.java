// imports
import java.awt.Color;
import java.awt.Graphics;

public class Tree {

    // instance variables
    private final int x, y;
    private final int dim;
    private final Color c;

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
            if (null != pa.dir) switch (pa.dir) {
                case "left" -> pa.x += pa.dim;
                case "right" -> pa.x -= pa.dim;
                case "up" -> pa.y += pa.dim;
                case "down" -> pa.y -= pa.dim;
                default -> {
                }
            }
        }
    }

    // collision
    public void collisionCheck(Player p) {
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