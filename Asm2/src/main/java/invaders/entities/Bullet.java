package invaders.entities;

public class Bullet {
    private String type;
    private int speed;

    public Bullet(String type, int speed) {
        this.type = type;
        this.speed = speed;
    }

    public void move() {
        // 根据speed移动子弹
    }

    // 其他Bullet类的方法和属性...
}
