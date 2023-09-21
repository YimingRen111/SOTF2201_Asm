package invaders.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.*;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;
	private List<Enemy> enemies;
	private List<Bunker> bunkers;
	private List<Bullet> bullets = new ArrayList<>();

	private boolean left;
	private boolean right;
	private boolean shoot;
	private List<List<Enemy>> enemyRows; //enemies groups as row
	private boolean directionChanged = false;

	public GameEngine(ConfigReader config){
		// read the config here
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

		enemyRows = new ArrayList<>();
		List<Enemy> currentRow = new ArrayList<>();
		double currentPosY = -1;

		for (Enemy enemy : enemies) {
			if (currentPosY == -1) {
				currentPosY = enemy.getPosition().getY();
			}

			if (enemy.getPosition().getY() == currentPosY) {
				currentRow.add(enemy);
			} else {
				enemyRows.add(currentRow);
				currentRow = new ArrayList<>();
				currentRow.add(enemy);
				currentPosY = enemy.getPosition().getY();
			}
		}
		if (!currentRow.isEmpty()) {
			enemyRows.add(currentRow);
		}

	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		List<List<Enemy>> rowsToRemove = new ArrayList<>();

		movePlayer();
		moveBullets();

		for (Bullet bullet : bullets) {
			bullet.up();
		}

		for (List<Enemy> row : enemyRows) {
			// get the first and last enemy of the row
			Enemy firstEnemy = row.get(0);
			Enemy lastEnemy = row.get(row.size() - 1);

			// if the first enemy was removed, then find a new one.
			while (!enemies.contains(firstEnemy) && !row.isEmpty()) {
				row.remove(0);
				if (!row.isEmpty()) {
					firstEnemy = row.get(0);
				}
			}

			// if the last enemy was removed, then find a new one.
			while (!enemies.contains(lastEnemy) && !row.isEmpty()) {
				row.remove(row.size() - 1);
				if (!row.isEmpty()) {
					lastEnemy = row.get(row.size() - 1);
				}
			}

			// check if the enemy reach the edge
			Enemy enemyToCheck = (firstEnemy.getDirection()) ? lastEnemy : firstEnemy;
			if (enemyToCheck.getPosition().getX() <= 0 || enemyToCheck.getPosition().getX() + enemyToCheck.getWidth() >= 600) {
				for (Enemy enemy : row) {
					enemy.changeDirection();
					enemy.down();
				}
				directionChanged = true;
			}

			if (row.isEmpty()) {
				rowsToRemove.add(row);
			}
		}

		enemyRows.removeAll(rowsToRemove);

		for (Enemy enemy : enemies) {
			enemy.update();
		}

		for(GameObject go: gameobjects){
			go.update();
		}



		handleCollisions();


		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables) {
			if (!ro.getLayer().equals(Renderable.Layer.FOREGROUND)) {
				continue;
			}
			if (ro.getPosition().getX() + ro.getWidth() >= 600) {
				ro.getPosition().setX(639 - ro.getWidth());
			}

			if (ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if (ro.getPosition().getY() + ro.getHeight() > 800) {
				ro.getPosition().setY(399 - ro.getHeight());
			}

			if (ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}


		// Check bullets for out of bounds
		List<Bullet> bulletsToRemove = new ArrayList<>();
		for (Bullet bullet : bullets) {
			if (bullet.getPosition().getY() <= 5 || bullet.getPosition().getY() > 800) {
				bulletsToRemove.add(bullet);
				bullet.markForDelete();
			}
		}
		bullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);
		directionChanged = false;
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

	private void handleCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<>();
		List<Enemy> enemiesToRemove = new ArrayList<>();

		for (Bullet bullet : bullets) {
			for (Enemy enemy : enemies) {
				if (bullet.getCollider().isColliding(enemy.getCollider())) {
					bulletsToRemove.add(bullet);
					enemiesToRemove.add(enemy);
					bullet.markForDelete();
					enemy.markForDelete();
				}
			}
		}

		bullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);

		enemies.removeAll(enemiesToRemove);
		gameobjects.removeAll(enemiesToRemove);
		renderables.removeAll(enemiesToRemove);
	}

}
