package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.*;
import invaders.entities.Builder.Bunker;
import invaders.entities.Builder.Enemy;
import invaders.entities.Factory.Bullet;
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
	private List<Bullet> playerBullets = new ArrayList<>();
	private List<Bullet> enemyBullets = new ArrayList<>();

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
		handleCollisions();
		movePlayer();
		moveBullets();

		for (Bullet bullet : playerBullets) {
			bullet.up();
		}
		for (Bullet bullet : enemyBullets) {
			bullet.down();
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
				this.directionChanged = true;
			}

			if (row.isEmpty()) {
				rowsToRemove.add(row);
			}
		}

		enemyRows.removeAll(rowsToRemove);

		for (Enemy enemy : enemies) {
			enemy.update();
		}

		// Enemy shoot, at most 3 projectile
		for (Enemy enemy : enemies) {
			if (Math.random() < 0.005 && enemyBullets.size() < 3) { // the chance to shoot
				Bullet bullet = enemy.shoot();
				if (bullet != null) {
					enemyBullets.add(bullet);
					gameobjects.add(bullet);
					renderables.add(bullet);
				}
			}
		}

		for(GameObject go: gameobjects){
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
//		for(Renderable ro: renderables) {
//			if (!ro.getLayer().equals(Renderable.Layer.FOREGROUND)) {
//				continue;
//			}
//			if (ro.getPosition().getX() + ro.getWidth() >= 600) {
//				ro.getPosition().setX(639 - ro.getWidth());
//			}
//
//			if (ro.getPosition().getX() <= 0) {
//				ro.getPosition().setX(1);
//			}
//
//			if (ro.getPosition().getY() + ro.getHeight() > 800) {
//				ro.getPosition().setY(399 - ro.getHeight());
//			}
//
//			if (ro.getPosition().getY() <= 0) {
//				ro.getPosition().setY(1);
//			}
//		}


		// Check playerBullets for out of bounds
		List<Bullet> bulletsToRemove = new ArrayList<>();
		removeOutsideBullets(bulletsToRemove, playerBullets);
		directionChanged = false;

		// Check enemyBullets for out of bounds
		removeOutsideBullets(bulletsToRemove, enemyBullets);

	}

	public void removeOutsideBullets(List<Bullet> bulletsToRemove, List<Bullet> playerBullets) {
		for (Bullet bullet : playerBullets) {
			if (bullet.getPosition().getY() <= 5 || bullet.getPosition().getY() > 800) {
				bulletsToRemove.add(bullet);
				bullet.markForDelete();
			}
		}
		playerBullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);
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
		if (shoot && playerBullets.isEmpty()) { // Ensure only one bullet on screen
			Bullet bullet = player.shoot();
			if (bullet != null) {
				playerBullets.add(bullet);
				gameobjects.add(bullet);
				renderables.add(bullet);
			}
			shoot = false;
		}
	}

	public Player getPlayer() {
		return player;
	}

	private void handleCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<>();
		List<Enemy> enemiesToRemove = new ArrayList<>();
		List<Bunker> bunkersToRemove = new ArrayList<>();

		// check if enemy is hit by playerBullets
		for (Bullet bullet : playerBullets) {
			for (Enemy enemy : enemies) {
				if (bullet.getCollider().isColliding(enemy.getCollider())) {
					bulletsToRemove.add(bullet);
					enemiesToRemove.add(enemy);
					bullet.markForDelete();
					enemy.markForDelete();
				}
			}
		}

		// Check if player is hit by enemyBullets
		for (Bullet bullet : enemyBullets) {
			if (!bullet.hasHitPlayer() && bullet.getCollider().isColliding(player.getCollider())) {
				player.reduceLives();
				bullet.hitPlayer();  // Mark the bullet as having hit the player
				bulletsToRemove.add(bullet);  // Mark the bullet for removal
				bullet.markForDelete();
				break;  // Exit the loop once the player is hit
			}
		}

		// Check if bunker is hit
		checkBulletBunkerCollisions();

		// Check if projectiles are collided
		handleBulletBulletCollisions();

		// Check bunkers that should be removed
		for (Bunker bunker : bunkers) {
			if (bunker.shouldBeRemoved()) {
				bunkersToRemove.add(bunker);
			}
		}

		playerBullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);

		enemies.removeAll(enemiesToRemove);
		gameobjects.removeAll(enemiesToRemove);
		renderables.removeAll(enemiesToRemove);

		bunkers.removeAll(bunkersToRemove);
		gameobjects.removeAll(bunkersToRemove);
		renderables.removeAll(bunkersToRemove);
	}

	private void checkBulletBunkerCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<>();
		for (Bullet bullet : playerBullets) {
			for (Bunker bunker : bunkers) {
				if (bunker.isColliding(bullet)) {
					bunker.hit();
					bulletsToRemove.add(bullet);
					bullet.markForDelete();
				}
			}
		}

		for (Bullet bullet : enemyBullets) {
			for (Bunker bunker : bunkers) {
				if (bunker.isColliding(bullet)) {
					bunker.hit();
					bulletsToRemove.add(bullet);
					bullet.markForDelete();
				}
			}
		}

		// remove the bullet shoot on bunker
		playerBullets.removeAll(bulletsToRemove);
		enemyBullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);
	}

	private void handleBulletBulletCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<>();
		for (Bullet playerBullet : playerBullets) {
			for (Bullet enemyBullet : enemyBullets) {
				if (playerBullet.getCollider().isColliding(enemyBullet.getCollider())) {
					bulletsToRemove.add(playerBullet);
					bulletsToRemove.add(enemyBullet);
					playerBullet.markForDelete();
					enemyBullet.markForDelete();
				}
			}
		}
		playerBullets.removeAll(bulletsToRemove);
		enemyBullets.removeAll(bulletsToRemove);
		gameobjects.removeAll(bulletsToRemove);
		renderables.removeAll(bulletsToRemove);
	}

}
