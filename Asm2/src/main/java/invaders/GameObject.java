package invaders;

import invaders.physics.Collider;

// contains basic methods that all GameObjects must implement
public interface GameObject {
    Collider collider = null;

    Collider getCollider();

    public void start();
    public void update();

}
