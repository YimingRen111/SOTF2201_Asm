package invaders.entities.State;

import javafx.scene.image.Image;

public interface BunkerState {
    Image getImage();
    void hit();
    boolean shouldBeRemoved();
}