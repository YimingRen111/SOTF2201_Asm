package invaders.entities;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.engine.GameEngine;
import invaders.entities.Factory.BulletFactory;
import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Player implements GameObject, Moveable, Damagable, Renderable {

    private ConfigReader configReader;

    private String colour;
    private int speed;
    private int lives;
    private int posX;
    private int posY;
    private final Animator anim = null;
    private double health = 100;

    private final double width = 25;
    private final double height = 30;
    private double gameWidth;
    private Image image = null;
    private Bullet bullet;
    private List<Bullet> bullets = new ArrayList<>();

    public Player(String colour, int speed, int lives, int posX, int posY, double gameWidth) {
        this.colour = colour;
        this.speed = speed;
        this.lives = lives;
        this.posX = posX;
        this.posY = posY;
        this.gameWidth = gameWidth;
        this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
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
        this.posX -= 1;
        if (posX < 0) {
            posX = 0;
        }
    }

    @Override
    public void right() {
        this.posX += 1;
        if (posX + 1.5*width > gameWidth) {
            posX = (int) (gameWidth - 1.5*width);
        }
    }


    public Bullet shoot(){
        double bulletStartX = posX + (width / 2); // Center of the player
        double bulletStartY = posY; // Top of the player
        Vector2D bulletPosition = new Vector2D(bulletStartX, bulletStartY);
        bullet = BulletFactory.createBullet("fast_straight", bulletPosition, Bullet.Direction.UP);
        bullets.add(bullet);
        System.out.println(bullet.getPosition().getY());
        return bullet;
    }



    public List<Bullet> getBullets() {
        return bullets;
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
    public void start() {

    }

    @Override
    public void update() {
    }
}
