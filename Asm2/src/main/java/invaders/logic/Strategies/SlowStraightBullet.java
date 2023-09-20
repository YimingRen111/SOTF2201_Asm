package invaders.logic.Strategies;

import invaders.physics.Vector2D;

public class SlowStraightBullet implements BulletStrategy{

    @Override
    public void move(Vector2D position) {
        // Slow movement logic
        position.setY(position.getY() - 2);
    }
}
