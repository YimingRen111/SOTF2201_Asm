package invaders.entities;

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

public class Player implements Moveable, Damagable, Renderable {

    private String colour;
    private int speed;
    private int lives;
    private int posX;
    private int posY;
    private final Animator anim = null;
    private double health = 100;

    private final double width = 25;
    private final double height = 30;
    private final Image image;

    private List<Bullet> bullets = new ArrayList<>();

    public Player(String colour, int speed, int lives, int posX, int posY) {
        this.colour = colour;
        this.speed = speed;
        this.lives = lives;
        this.posX = posX;
        this.posY = posY;
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
    }

    @Override
    public void right() {
        this.posY += 1;
    }

    public void shoot(){
        Vector2D bulletPosition = new Vector2D(posX, posY - this.height);
        Bullet bullet = BulletFactory.createBullet("fast_straight", bulletPosition, Bullet.Direction.UP);
        bullets.add(bullet);
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

}
