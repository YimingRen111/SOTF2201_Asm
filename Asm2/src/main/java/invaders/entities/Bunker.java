package invaders.entities;

import invaders.GameObject;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements GameObject, Damagable, Renderable {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image = new Image(new File("src/main/resources/bunker.png").toURI().toString());

    public Bunker(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
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
        return null;
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
}
