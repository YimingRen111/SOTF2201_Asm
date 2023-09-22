package invaders.entities;

import invaders.ConfigReader;
import invaders.entities.Builder.Bunker;
import invaders.entities.Builder.Enemy;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class EntityViewImpl implements EntityView {
    private Renderable entity;
    private Vector2D position;
    private boolean delete = false;
    private ImageView node;

    public EntityViewImpl(Renderable entity) {
        this.entity = entity;
        this.position = entity.getPosition();
        node = new ImageView(entity.getImage());
        node.setViewOrder(getViewOrder(entity.getLayer()));

        // Adjust the size based on the type of entity
        if (entity instanceof Player) {
            node.setFitWidth(ConfigReader.PLAYER_WIDTH);
            node.setFitHeight(ConfigReader.PLAYER_HEIGHT);
        } else if (entity instanceof Enemy) {
            node.setFitWidth(ConfigReader.ENEMY_WIDTH);
            node.setFitHeight(ConfigReader.ENEMY_HEIGHT);
        } else if (entity instanceof Bunker) {
            node.setFitWidth(ConfigReader.BUNKER_WIDTH);
            node.setFitHeight(ConfigReader.BUNKER_HEIGHT);
        }

        update(0.0, 0.0);
    }

    private static double getViewOrder(Renderable.Layer layer) {
        switch (layer) {
            case BACKGROUND: return 100.0;
            case FOREGROUND: return 50.0;
            case EFFECT: return 25.0;
            default: throw new IllegalStateException("Javac doesn't understand how enums work so now I have to exist");
        }
    }

    public Renderable getEntity() {
        return this.entity;
    }


    @Override
    public void update(double xViewportOffset, double yViewportOffset) {
        if (!node.getImage().equals(entity.getImage())) {
            node.setImage(entity.getImage());
        }
        node.setX(entity.getPosition().getX() - xViewportOffset); // Use entity's position
        node.setY(entity.getPosition().getY() - yViewportOffset); // Use entity's position
        node.setPreserveRatio(true);
        delete = false;
    }


    @Override
    public boolean matchesEntity(Renderable entity) {
        return this.entity.equals(entity);
    }

    @Override
    public void markForDelete() {
        delete = true;
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public boolean isMarkedForDelete() {
        return delete;
    }
}
