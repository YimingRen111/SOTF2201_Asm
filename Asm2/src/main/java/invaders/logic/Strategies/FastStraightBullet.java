package invaders.logic.Strategies;

import invaders.entities.Factory.Bullet;
import invaders.physics.Vector2D;

public class FastStraightBullet implements BulletStrategy{
    @Override
    public void move(Vector2D position, Bullet.Direction direction) {
        if (direction == Bullet.Direction.UP) {
            position.setY(position.getY() - 5);
        } else if (direction == Bullet.Direction.DOWN) {
            position.setY(position.getY() + 5);
        }
    }
}
