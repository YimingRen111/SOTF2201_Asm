package invaders.entities;

import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

public abstract class Entity implements Renderable {
    protected Vector2D position;
    protected boolean markedForDelete = false;

    // ... other common properties and methods ...

    public void markForDelete() {

        this.markedForDelete = true;
    }

    public boolean isMarkedForDelete() {
        return markedForDelete;
    }

    // ... other methods ...
}
