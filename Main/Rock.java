// imports
import java.awt.Color;
import java.awt.Graphics;

public class Rock {

    // instance variables
    private final int x;
    private final int y;
    private final int dim;
    private final Color c;

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