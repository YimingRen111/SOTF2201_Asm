package invaders.entities;

import com.sun.javafx.scene.traversal.Direction;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public class Bullet {
    private Vector2D position;
    private int speed;
    private int damage;
    private boolean isAlive = true;

    private Direction direction;

    public enum Direction {
        UP, DOWN
    }

    public Bullet(Vector2D position, int speed, int damage, Direction direction) {
        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.direction = direction;
    }

    public Vector2D getPosition() {
        return position;
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
        if (direction == Direction.UP) {
            this.position.setY(this.position.getY() - speed);
        }
    }

    public void down() {
        if (direction == Direction.DOWN) {
            this.position.setY(this.position.getY() + speed);
        }
    }

}
