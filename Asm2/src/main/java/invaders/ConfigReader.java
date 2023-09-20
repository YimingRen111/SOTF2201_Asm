package invaders;

import invaders.entities.Bullet;
import invaders.entities.Bunker;
import invaders.entities.Enemy;
import invaders.entities.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

	public static final double PLAYER_WIDTH = 50;
	public static final double PLAYER_HEIGHT = 50;
	public static final double ENEMY_WIDTH = 40;
	public static final double ENEMY_HEIGHT = 40;
	public static final double BUNKER_WIDTH = 60;
	public static final double BUNKER_HEIGHT = 30;

	private JSONObject configData;

	public ConfigReader(String configFilePath) {
		loadConfig(configFilePath);
	}

	private void loadConfig(String configFilePath) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(configFilePath));
			configData = (JSONObject) obj;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public int getGameWidth() {
		JSONObject gameConfig = (JSONObject) configData.get("Game");
		JSONObject size = (JSONObject) gameConfig.get("size");
		return ((Long) size.get("x")).intValue();
	}

	public int getGameHeight() {
		JSONObject gameConfig = (JSONObject) configData.get("Game");
		JSONObject size = (JSONObject) gameConfig.get("size");
		return ((Long) size.get("y")).intValue();
	}

	public Player getPlayer() {
		JSONObject playerConfig = (JSONObject) configData.get("Player");
		String colour = (String) playerConfig.get("colour");
		int speed = ((Long) playerConfig.get("speed")).intValue();
		int lives = ((Long) playerConfig.get("lives")).intValue();
		JSONObject position = (JSONObject) playerConfig.get("position");
		int posX = ((Long) position.get("x")).intValue();
		int posY = ((Long) position.get("y")).intValue();
		return new Player(colour, speed, lives, posX, posY, getGameWidth());
	}

	public List<Bunker> getBunkers() {
		JSONArray bunkersArray = (JSONArray) configData.get("Bunkers");
		List<Bunker> bunkersList = new ArrayList<>();
		for (Object bunkerObj : bunkersArray) {
			JSONObject bunkerConfig = (JSONObject) bunkerObj;
			JSONObject position = (JSONObject) bunkerConfig.get("position");
			int posX = ((Long) position.get("x")).intValue();
			int posY = ((Long) position.get("y")).intValue();
			JSONObject size = (JSONObject) bunkerConfig.get("size");
			int width = ((Long) size.get("x")).intValue();
			int height = ((Long) size.get("y")).intValue();
			bunkersList.add(new Bunker(posX, posY, width, height));
		}
		return bunkersList;
	}

	public List<Enemy> getEnemies() {
		JSONArray enemiesArray = (JSONArray) configData.get("Enemies");
		List<Enemy> enemiesList = new ArrayList<>();
		for (Object enemyObj : enemiesArray) {
			JSONObject enemyConfig = (JSONObject) enemyObj;
			JSONObject position = (JSONObject) enemyConfig.get("position");
			int posX = ((Long) position.get("x")).intValue();
			int posY = ((Long) position.get("y")).intValue();
			String projectileStrategy = (String) enemyConfig.get("projectile");
			enemiesList.add(new Enemy(posX, posY, projectileStrategy));
		}
		return enemiesList;
	}
}
