package invaders.entities.Builder;

public class BunkerBuilder {
    private int posX;
    private int posY;
    private int width;
    private int height;

    public BunkerBuilder setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public BunkerBuilder setPosY(int posY) {
        this.posY = posY;
        return this;
    }

    public BunkerBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public BunkerBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public Bunker build() {
        return new Bunker(posX, posY, width, height);
    }
}
