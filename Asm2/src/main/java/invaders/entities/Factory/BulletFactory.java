package invaders.entities.Factory;

import invaders.logic.Strategies.FastStraightBullet;
import invaders.logic.Strategies.SlowStraightBullet;
import invaders.physics.Vector2D;

public class BulletFactory {
    public static Bullet createBullet(String type, Vector2D position, Bullet.Direction direction, Bullet.Shooter shooter) {
        switch (type) {
            case "fast_straight":
                return new Bullet(position, 1, direction, new FastStraightBullet(), shooter);
            case "slow_straight":
                return new Bullet(position, 1, direction, new SlowStraightBullet(), shooter); // Assuming speed is 5 for slow bullet
            default:
                throw new IllegalArgumentException("Unknown bullet type: " + type);
        }
    }
}
