package invaders.entities.Factory;

import invaders.entities.Bullet;

public class BulletFactory {
    public static Bullet createBullet(String type) {
        if (type.equals("fast_straight")) {
            return new Bullet("fast_straight", 2);
        } else if (type.equals("slow_straight")) {
            return new Bullet("slow_straight", 1);
        }
        return null;
    }
}
