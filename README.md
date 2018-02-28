# adventure
A text input based game. Uses the [Gson](https://github.com/google/gson) library.

# Design
1. The classes `Layout.java`, `Player.java`, `Monster.java`, `Item.java`, `Room.java` and `Direction.java` model the Layout JSON object, the Player JSON Object, the Monster JSON object, the Room JSON Object and the Direction JSON Object.
2. The class `Game.java` models the game and stores the states that change throughout the course of the time, such as the room the player is in currently, and the game layout, and the player's maximum health.
3. The class `Validator.java` contains `mapValidator()` and `floorPlanValidator()` functions validate the game layout.
4. The class `JsonAccessor.java` uses [Unirest](http://unirest.io/java.html) to access the JSON file on the CS 126 site. [for Adventure 2.0, the JSON is loaded off my personal website.]
5. The class `Interface.java` is the User Interface for the Adventure game. Its `main()` function is the one to be run to play the game.
