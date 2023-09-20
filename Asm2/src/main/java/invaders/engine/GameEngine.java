package invaders.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.Bullet;
import invaders.entities.Bunker;
import invaders.entities.Enemy;
import invaders.entities.Player;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private ConfigReader config;
	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;
	private List<Enemy> enemies;
	private List<Bunker> bunkers;
	private List<Bullet> bullets = new ArrayList<>();

	private boolean left;
	private boolean right;
	private boolean shoot;

	public GameEngine(ConfigReader config){
		// read the config here
		this.config = config;
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();

		// Initialize player
		player = config.getPlayer();
		gameobjects.add(player);
		renderables.add(player);

		// Initialize enemies
		enemies = config.getEnemies();
		gameobjects.addAll(enemies);
		renderables.addAll(enemies);

		// Initialize bunkers
		bunkers = config.getBunkers();
		gameobjects.addAll(bunkers);
		renderables.addAll(bunkers);

	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();
		moveBullets();
		for(GameObject go: gameobjects){
			go.update();
		}
		for (Bullet bullet : bullets) {
			bullet.up();
		}
		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= 600) {
				ro.getPosition().setX(639-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() > 800) {
				ro.getPosition().setY(399-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}

	}

	public List<Renderable> getRenderables(){
		return renderables;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		shoot = true;
		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}

	private void moveBullets() {
		if(shoot){
			Bullet bullet = player.shoot();
			bullets.add(bullet);
			gameobjects.add(bullet);
			renderables.add(bullet);  // Add bullet to renderables
			shoot = false;
		}

	}

	public Player getPlayer() {
		return player;
	}
}
