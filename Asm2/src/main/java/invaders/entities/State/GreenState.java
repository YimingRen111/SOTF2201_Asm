package invaders.entities.State;

import invaders.entities.Builder.Bunker;
import invaders.entities.State.YellowState;
import javafx.scene.image.Image;

import java.io.File;

public class GreenState implements BunkerState {
    private Bunker bunker;
    private Image image;

    public GreenState(Bunker bunker, String imagePath) {
        this.bunker = bunker;
        this.image = new Image(new File(imagePath).toURI().toString());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void hit() {
        bunker.setState(new YellowState(bunker));
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}