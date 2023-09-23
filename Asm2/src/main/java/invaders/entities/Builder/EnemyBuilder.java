package invaders.entities.Builder;

public class EnemyBuilder {
    private double posX;
    private double posY;
    private String projectileStrategy;
    private double width;
    private double height;

    public EnemyBuilder setPosX(double posX) {
        this.posX = posX;
        return this;
    }

    public EnemyBuilder setPosY(double posY) {
        this.posY = posY;
        return this;
    }

    public EnemyBuilder setProjectileStrategy(String projectileStrategy) {
        this.projectileStrategy = projectileStrategy;
        return this;
    }

    public EnemyBuilder setWidth(double width) {
        this.width = width;
        return this;
    }

    public EnemyBuilder setHeight(double height) {
        this.height = height;
        return this;
    }


    public Enemy build() {
        return new Enemy(posX, posY, projectileStrategy, width, height);
    }
}
