package invaders.logic.Strategies;

import invaders.entities.Factory.Bullet;
import invaders.physics.Vector2D;

public interface BulletStrategy {
    void move(Vector2D position, Bullet.Direction direction);
}
