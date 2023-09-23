package invaders.entities.State;

import invaders.entities.Builder.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class YellowState implements BunkerState {
    private Bunker bunker;
    private Image image;

    public YellowState(Bunker bunker) {
        this.bunker = bunker;
        this.image = new Image(new File("src/main/resources/bunker_yellow.png").toURI().toString());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void hit() {
        bunker.setState(new RedState(bunker));
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}