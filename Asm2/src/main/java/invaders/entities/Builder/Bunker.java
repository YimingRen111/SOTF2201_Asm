package invaders.entities.Builder;

import invaders.GameObject;
import invaders.entities.Entity;
import invaders.entities.Factory.Bullet;
import invaders.entities.State.BunkerState;
import invaders.entities.State.GreenState;
import invaders.logic.Damagable;
import invaders.physics.BoxCollider;
import invaders.physics.Collider;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

// Bunker.java
public class Bunker extends Entity implements GameObject, Damagable, Renderable {

    private double posX;
    private double posY;
    private double width;
    private double height;
    private BunkerState state;
    private BoxCollider collider;

    public Bunker(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.state = new GreenState(this, "src/main/resources/bunker_green.png");
        this.collider = new BoxCollider(width, height, new Vector2D(posX,posY));
    }

    public void hit() {
        state.hit();
    }

    public void setState(BunkerState state) {
        this.state = state;
    }

    public boolean isColliding(Bullet bullet) {
        return collider.isColliding(bullet.getCollider());
    }

    public boolean shouldBeRemoved() {
        return state.shouldBeRemoved();
    }

    @Override
    public Collider getCollider() {
        return this.collider;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void takeDamage(double amount) {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public Image getImage() {
        return state.getImage();
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(this.posX, this.posY);
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }
}
