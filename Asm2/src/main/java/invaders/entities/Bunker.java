package invaders.entities;

import invaders.GameObject;
import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public class Bunker implements GameObject, Damagable, Renderable {
    public Bunker(int posX, int posY, int width, int height) {
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
