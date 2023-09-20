package invaders.entities.Factory;

import invaders.entities.Bullet;
import invaders.physics.Vector2D;

public class BulletFactory {
    public static Bullet createBullet(String type, Vector2D position, Bullet.Direction direction) {
        switch (type) {
            case "fast_straight":
                return new Bullet(position, 10, 1, direction);
            // Add other type of bullet
            default:
                throw new IllegalArgumentException("Unknown bullet type: " + type);
        }
    }
}
