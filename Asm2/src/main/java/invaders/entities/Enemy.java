package invaders.entities;

import invaders.GameObject;
import invaders.entities.Factory.BulletFactory;
import invaders.physics.BoxCollider;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity implements GameObject, Moveable, Renderable {
    private double posX;
    private double posY;
    private Vector2D position;
    private String Strategy;

    private Image image;
    private List<Bullet> bullets = new ArrayList<>();
    private BoxCollider collider;
    private final double width = 50;
    private final double height = 37;

    private boolean moveRight;
    private static final double MOVE_AMOUNT = 0.2; // distance for each movement
    private static final double DOWN_AMOUNT = 5; // When reach the edge, move down


    public Enemy(int posX, int posY, String projectileStrategy) {
        this.posX = posX;
        this.posY = posY;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString());
        this.Strategy = projectileStrategy;
        this.collider = new BoxCollider(width, height, new Vector2D(posX,posY));
        this.moveRight = true;
        this.position = new Vector2D(posX,posY);
    }

    public void shoot() {
        Vector2D bulletPosition = new Vector2D(posX, posY + this.getHeight());
        Bullet bullet = BulletFactory.createBullet("fast_straight", bulletPosition, Bullet.Direction.DOWN);
        bullets.add(bullet);
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {
        this.posY +=  DOWN_AMOUNT;
    }

    @Override
    public void left() {
        this.posX -=  MOVE_AMOUNT;
    }

    @Override
    public void right() {
        this.posX +=  MOVE_AMOUNT;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return this.image.getWidth();
    }

    @Override
    public double getHeight() {
        return this.image.getHeight();
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(this.posX, this.posY);
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void start() {

    }

    public boolean getDirection(){
        return moveRight;
    }
    public void changeDirection() {
        moveRight = !moveRight;
    }

    @Override
    public void update() {
        if (moveRight) {
            right();
        } else {
            left();
        }
    }
}
