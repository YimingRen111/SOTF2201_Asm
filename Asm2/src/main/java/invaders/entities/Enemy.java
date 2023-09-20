package invaders.entities;

import invaders.GameObject;
import invaders.entities.Factory.BulletFactory;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Enemy implements Moveable, Renderable {
    private Vector2D position;
    private Image image;
    private List<Bullet> bullets = new ArrayList<>();

    public Enemy(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString());
    }

    public void shoot() {
        Vector2D bulletPosition = new Vector2D(this.position.getX(), this.position.getY() + this.getHeight());
        Bullet bullet = BulletFactory.createBullet("fast_straight", bulletPosition, Bullet.Direction.DOWN);
        bullets.add(bullet);
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public Vector2D getPosition() {
        return null;
    }

    @Override
    public Layer getLayer() {
        return null;
    }

}
