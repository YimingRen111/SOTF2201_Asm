package invaders.entities;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.Factory.Bullet;
import invaders.entities.Factory.BulletFactory;
import invaders.logic.Damagable;
import invaders.physics.BoxCollider;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity implements GameObject, Moveable, Damagable, Renderable {

    private ConfigReader configReader;

    private String colour;
    private int speed;
    private int lives;
    private int posX;
    private int posY;
    private final Animator anim = null;
    private double health = 100;
    private final double width;
    private final double height;
    private double gameWidth;
    private Image image;
    private BoxCollider collider;


    public Player(String colour, int speed, int lives, int posX, int posY, double gameWidth, double width, double height) {
        this.colour = colour;
        this.speed = speed;
        this.lives = lives;
        this.posX = posX;
        this.posY = posY;
        this.gameWidth = gameWidth;
        this.width = width;
        this.height = height;
        this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
        this.collider = new BoxCollider(width, height, new Vector2D(posX, posY));
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.posX -= speed;
        if (posX < 0) {
            posX = 0;
        }
    }

    @Override
    public void right() {
        this.posX += speed;
        if (posX + width > gameWidth) {
            posX = (int) (gameWidth - width);
        }
    }


    public Bullet shoot(){
        double bulletStartX = posX + (width / 2); // Center of the player
        double bulletStartY = posY; // Top of the player
        Vector2D bulletPosition = new Vector2D(bulletStartX, bulletStartY);
        return BulletFactory.createBullet("slow_straight", bulletPosition, Bullet.Direction.UP, Bullet.Shooter.PLAYER);
    }

    public void reduceLives(){
        lives--;
        System.out.println("Player Lives: "+ lives);
        if (lives <= 0) {
            System.out.println("Game Over!");
            System.exit(0); // if lives <=0 , the game ends.
        }
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(posX, posY);
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }


    @Override
    public Collider getCollider() {
        collider.getPosition().setX(posX);
        collider.getPosition().setY(posY);
        return collider;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }


}
