package invaders.entities.Builder;

import invaders.GameObject;
import invaders.entities.Factory.Bullet;
import invaders.entities.Entity;
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
    private final double width;
    private final double height;

    private boolean moveRight;
    private static final double MOVE_AMOUNT = 0.1; // distance for each movement
    private static final double DOWN_AMOUNT = 4; // When reach the edge, move down


    public Enemy(double posX, double posY, String projectileStrategy, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString());
        this.Strategy = projectileStrategy;
        this.width = width;
        this.height = height;
        this.collider = new BoxCollider(width, height, new Vector2D(posX,posY));
        this.moveRight = true;
        this.position = new Vector2D(posX,posY);
    }

    public Bullet shoot() {
        Vector2D bulletPosition = new Vector2D(posX, posY + this.getHeight());
        Bullet bullet = BulletFactory.createBullet(this.Strategy, bulletPosition, Bullet.Direction.DOWN, Bullet.Shooter.ENEMY);
        bullets.add(bullet);
        return bullet;
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {
        this.posY +=  DOWN_AMOUNT;
        this.collider.getPosition().setY(this.posY);
    }

    @Override
    public void left() {
        this.posX -=  MOVE_AMOUNT;
        this.collider.getPosition().setX(this.posX);
    }

    @Override
    public void right() {
        this.posX +=  MOVE_AMOUNT;
        this.collider.getPosition().setX(this.posX);
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
