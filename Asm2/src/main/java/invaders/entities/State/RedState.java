package invaders.entities.State;

import invaders.entities.Builder.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class RedState implements BunkerState {
    private Bunker bunker;
    private Image image;
    private boolean markedForRemoval = false;

    public RedState(Bunker bunker) {
        this.bunker = bunker;
        this.image = new Image(new File("src/main/resources/bunker_red.png").toURI().toString());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void hit() {
        markedForRemoval = true;
    }

    @Override
    public boolean shouldBeRemoved() {
        return markedForRemoval;
    }
}