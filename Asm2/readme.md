
# Space Invaders Game

## Description
This is a classic Space Invaders game where the player controls a spaceship and tries to eliminate incoming enemies while avoiding their projectiles.

## How to Play

### Starting the Game
To start the game, use the following command:
```
gradle run
```

### Gameplay

1. **Player's Spaceship**: Once the game starts, you'll see a spaceship controlled by the player at the bottom of the screen. The player's shooting mode is set to slow by default.
2. **Bunkers**: There are three green bunkers that act as a shield for the player.
3. **Enemies**: A total of 27 enemies appear from the top of the screen. Enemies have two shooting modes: fast and slow.

### Controls

- **Move Left**: Left Arrow Key
- **Move Right**: Right Arrow Key
- **Shoot**: Spacebar

### Game Mechanics

- The player can shoot only one bullet at a time.
- Enemies move from the top to the bottom of the screen. When the first enemy of a row reaches the screen's edge, the entire row moves down.
- When a player's bullet hits an enemy, the enemy is eliminated, and the speed of the remaining enemies increases by 1.05 times.
- Enemies shoot bullets randomly, with a maximum of 3 enemy bullets on the screen at a time.
- If an enemy bullet hits the player, the player loses a life. The remaining lives are displayed in the terminal.
- Bunkers change color based on their state: Green -> Yellow -> Red. Bullets from both the player and enemies cannot pass through bunkers. If an enemy touches a bunker, the enemy is removed.

### Game End Conditions

1. Player's lives are exhausted (default lives are 5, which can be adjusted in `config.json`).
2. All enemies are eliminated by the player.
3. Enemies reach the bottom of the screen and touch the player.

## Config.json Modifications

You can customize the game settings by modifying the `config.json` file. Here are some of the configurations you can adjust:

### Game Window Settings
```json
"Game": {
    "size": {
      "x": 600, // Game window width
      "y": 800  // Game window height
    }
}
```

### Player Settings
```json
"Player": {
    "colour": "green", // Spaceship color (currently only one color is available)
    "speed": 2, // Spaceship horizontal movement speed
    "lives": 5, // Player's lives
    "width": 50, // Spaceship width
    "height": 50, // Spaceship height
    "position": {
      "x": 300, // Spaceship starting position (X-coordinate)
      "y": 750  // Spaceship starting position (Y-coordinate)
    }
}
```

### Bunker Settings
```json
"Bunkers": [
    {
      "width": 74,
      "height": 54,
      "position": {
        "x": 75,
        "y": 600
      },
      "size": {
        "x": 100,
        "y": 25
      }
    }
]
```

### Enemy Settings
```json
{
      "speed": 0.1,
      "width": 44,
      "height": 33,
      "position": {
        "x": 50,
        "y": 100
      },
      "projectile": "fast_straight" // Enemy shooting modes: "fast_straight" for fast, another mode for slow
}
```
