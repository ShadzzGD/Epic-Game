// MURPHY, PATRICK

// imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MyGame extends Game  {
        
        // screen variables
        public static final String TITLE = "Survival Game";
        public static final int SCREEN_WIDTH = 500;
        public static final int SCREEN_HEIGHT = 500;

        // declare variables
        // classes
        Player p;
        Tree[] t;
        Rock[] r;
        PassiveAnimal pa;
        Color maroon;
        Color darkerMaroon;
        Color brown;
        Random random;

        // integers
        int pWidth;
        int selectorX, selectorY;
        int selected;

        // booleans
        boolean canInteract;
		
	public MyGame() {
                // initialize variables
                // classes
                // random
                random = new Random();

                // color
                maroon = new Color(128, 0, 0);
                brown = new Color(150, 75, 0);
                darkerMaroon = new Color(150, 0, 0);

                // player
                p = new Player(100, 200, 25, 25, Color.ORANGE);

                // passive animal
                pa = new PassiveAnimal(275, 125, 25, brown);

                
                // arrays
                // rock
                r = new Rock[3];
                r[0] = new Rock(400, 175, 25, Color.GRAY);
                r[1] = new Rock(250, 275, 25, Color.GRAY);
                r[2] = new Rock(75, 250, 25, Color.GRAY);

                // tree
                t = new Tree[4];
                t[0] = new Tree(25, 100, 25, Color.GREEN);
                t[1] = new Tree(350, 150, 25, Color.GREEN);
                t[2] = new Tree(125, 50, 25, Color.GREEN);
                t[3] = new Tree(50, 300, 25, Color.GREEN);

                // integers
                pWidth = 0;
                selectorX = 100;
                selectorY = 380;

                // booleans
                canInteract = true;
                selected = 1;
        }

	public void update() {

                p.barrierCheck(SCREEN_WIDTH, SCREEN_HEIGHT);
                p.naturalDrain();
                for (Rock r1 : r) {
                    r1.collisionCheck(p);
                }
                for (Tree t1 : t) {
                    t1.collisionCheck(p);
                }

                // awesome progress bar for everything interactive
                if (p.startProgressBar == true) {
                        canInteract = false;
                        pWidth += 1;
                        if (pWidth == ((p.dim * 2) - 5)) {
                                p.startProgressBar = false;
                                canInteract = true;
                                pWidth = 0;
                                if (p.mWood == true) {
                                        p.wood += 1;
                                } else if (p.mStone == true) {
                                        p.stone += 1;
                                } else if (p.hurtAnimal == true) {
                                        pa.anotherWidthVar -= 15;
                                } else if (p.eating == true) {
                                        p.food += 10;
                                        p.energy += 11;
                                        p.meat -= 1;
                                        if (p.food >= 50) {
                                                p.food = 50;
                                        }
                                }
                                p.energy -= 1;
                                p.eating = false;
                                p.hurtAnimal = false;
                                p.mWood = false;
                                p.mStone = false;
                        }
                }

                // passive animal
                pa.barrierCheck(SCREEN_WIDTH, SCREEN_HEIGHT);
                pa.movementAI(random);
                pa.collision(p);
                for (Tree t1 : t) {
                    t1.animalCollision(pa);
                }
                for (Rock r1 : r) {
                    r1.animalCollision(pa);
                }

                if (pa.anotherWidthVar == 0) {
                        pa.alive = false;
                        p.meat += 2;
                        pa.anotherWidthVar = ((pa.dim * 2) - 5);
                }

                if (pa.respawnTimer == 2500) {
                        pa.alive = true;
                        pa.respawnTimer = 0;
                }
                pa.respawnTimer += 1;

        }

	public void draw(Graphics pen) {

                // draw background
                pen.setColor(Color.BLACK);
                pen.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

                // draw game
                // draw blocks
                for (Rock r1 : r) {
                    r1.draw(pen);
                }

                // draw trees
                for (Tree t1 : t) {
                    t1.draw(pen);
                }

                // draw passive animal
                if (pa.alive == true) {
                        pa.draw(pen);
                        pa.drawProgressBar(pen);
                        pa.drawSecondOne(pen);
                }

                // check if the player is alive 
                if (p.alive == true) {
                        // draw player
                        p.draw(pen);
                        // draw player progress bar
                        if (p.startProgressBar == true) {
                                p.drawProgressBar(pen);
                                p.drawSecondOne(pen, pWidth);
                        }
                }

                // draw death screen
                p.death(pen, SCREEN_WIDTH);

                // draw inventory
                pen.setColor(maroon);
                pen.fillRect(0, 375, SCREEN_WIDTH, 100);
                pen.setFont(new Font("Arial", 1, 20));
                pen.setColor(Color.WHITE);
                pen.drawString("Wood: " + Integer.toString(p.wood), 0, 400);
                pen.drawString("Stone: " + Integer.toString(p.stone), 0, 425);
                pen.drawString("Meat: " + Integer.toString(p.meat), 0, 450);
                pen.drawString(Integer.toString(p.food) + " :Hunger", 375, 400);
                pen.drawString(Integer.toString(p.energy) + " :Energy", 375, 425);
                // draw hot bar
                pen.setColor(darkerMaroon);
                pen.fillRect(100, 380, 85, 75);
                pen.fillRect(190, 380, 85, 75);
                pen.fillRect(280, 380, 85, 75);
                // draw selector
                pen.setColor(Color.WHITE);
                pen.drawRect(selectorX, selectorY, 85, 75);
                // draw items
                pen.setFont(new Font("Arial", 1, 15));
                pen.drawString("Dagger", 115, 420);
        }
        
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {

        // player movement and interactions
        if (p.startProgressBar == false && p.energy > 0) {
                if (p.alive == true) {
                        if (ke.getKeyChar() == 's') {
                                p.down();
                                p.energy -= 1;
                        } else if (ke.getKeyChar() == 'w') {
                                p.up();
                                p.energy -= 1;
                        } else if (ke.getKeyChar() == 'a') {
                                p.left();
                                p.energy -= 1;
                        } else if (ke.getKeyChar() == 'd') {
                                p.right();
                                p.energy -= 1;
                        } else if (ke.getKeyChar() == 'e' && canInteract == true) {
                                for (Tree t1 : t) {
                                    t1.checkForInteraction(p);
                                }
                                for (Rock r1 : r) {
                                    r1.checkForInteraction(p);
                                }
                                pa.checkForInteraction(p);
                        } else if (ke.getKeyChar() == 'c') {
                                if (p.meat >= 1) {
                                        p.startProgressBar = true;
                                        p.eating = true;
                                }
                        }
                }
                
        }

        // selector movement
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT && selectorX < 280) {
                selectorX += 90;
                selected += 1;
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT && selectorX > 100) {
                selectorX -= 90;
                selected -= 1;
        }

        // SIMPLY FOR DEBUGGING | COMMENTED OUT = NOT IN USE
        //if (ke.getKeyChar() == 'i') {
        //        pa.y -= pa.dim;
        //} else if (ke.getKeyChar() == 'k') {
        //        pa.y += pa.dim;
        //} else if (ke.getKeyChar() == 'j') {
        //        pa.x -= pa.dim;
        //} else if (ke.getKeyChar() == 'l') {
        //        pa.x += pa.dim;
        //}
    }

    @Override
    public void mouseClicked(MouseEvent ke) {}

    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
        
    //Launches the Game
    public static void main(String[] args) { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }
}