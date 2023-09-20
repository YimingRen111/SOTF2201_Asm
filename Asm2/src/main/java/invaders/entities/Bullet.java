package invaders.entities;

import com.sun.javafx.scene.traversal.Direction;
import invaders.GameObject;

import invaders.logic.Strategies.BulletStrategy;

import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Bullet implements Renderable, GameObject {
    private Vector2D position;
    private BulletStrategy strategy;
    private int damage;
    private int speed;
    private boolean isAlive = true;
    private Direction direction;
    private Image image;
    private final double width = 5;
    private final double height = 10;

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    public double getSpeed() {
        return speed;
    }

    public enum Direction {
        UP, DOWN
    }

    public Bullet(Vector2D position, int damage, Direction direction, BulletStrategy strategy) {
        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.direction = direction;
        this.strategy = strategy;
        this.image = new Image(new File("src/main/resources/bullet.png").toURI().toString());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void up() {
        strategy.move(position);
    }

    public void down() {
        strategy.move(position);
    }

}
