package invaders.logic.Strategies;

import invaders.entities.Bullet;
import com.sun.javafx.scene.traversal.Direction;
import invaders.physics.Vector2D;

public class FastStraightBullet implements BulletStrategy{

    @Override
    public void move(Vector2D position) {
        // Fast movement logic
        position.setY(position.getY() - 5);
    }
}
