package invaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */
	public static void parse(String path) {

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Game section:
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");
			// reading a coordinate from the nested section within the game
			// note that the game x and y are of type Long (i.e. they are integers)
			Long gameX = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			Long gameY = (Long) ((JSONObject) jsonGame.get("size")).get("y");


			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				Double positionX = (Double) ((JSONObject) jsonEnemy.get("position")).get("x");
				Double positionY = (Double) ((JSONObject) jsonEnemy.get("position")).get("y");
				String projectileStrategy = (String) jsonEnemy.get("projectile");
			}

			// Reading the Player section:
			JSONObject jsonPlayer = (JSONObject) jsonObject.get("Player");
			String playerColor = (String) jsonPlayer.get("colour");
			Long playerSpeed = (Long) jsonPlayer.get("speed");
			Long playerLives = (Long) jsonPlayer.get("lives");
			JSONObject playerPosition = (JSONObject) jsonPlayer.get("position");
			Double playerPosX = (Double) playerPosition.get("x");
			Double playerPosY = (Double) playerPosition.get("y");

			// Reading the Bunkers array:
			JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");
			for (Object obj : jsonBunkers) {
				JSONObject jsonBunker = (JSONObject) obj;
				JSONObject bunkerPosition = (JSONObject) jsonBunker.get("position");
				Double bunkerPosX = (Double) bunkerPosition.get("x");
				Double bunkerPosY = (Double) bunkerPosition.get("y");
				JSONObject bunkerSize = (JSONObject) jsonBunker.get("size");
				Long bunkerSizeX = (Long) bunkerSize.get("x");
				Long bunkerSizeY = (Long) bunkerSize.get("y");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Your main method will probably be in another file!
	 * 
	 * @param args First argument is the path to the config file
	 */
	public static void main(String[] args) {
		// if a command line argument is provided, that should be used as the path
		// if not, you can hard-code a default. e.g. "src/main/resources/config.json"
		// this makes it easier to test your program with different config files
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "src/main/resources/config.json";
		}
		// parse the file:
		ConfigReader.parse(configPath);
	}

}
