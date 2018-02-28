import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * . Class that represents a player in the game. Stores all things whose state changes during the
 * game. Contains functions called by Interface.java. Provides wrapper functions for functions from
 * Layout, Room, Direction.
 *
 * @author diproray
 */
public class Game {

  // Member variables.
  private Room currentRoom;
  private Layout gameLayout;
  public double playerMaxHealth;
  // My Adventure JSON by default sets all monsters health to 750 initially.
  public static double monsterMaxHealth = 750;
  public static double experience = 0;

  // Constructors.
  /**
   * . Constructor for Game object. Catches Gson.JsonParseException and displays a termination
   * message.
   *
   * @throws Exception thrown when starting room name is not found in Room[] of Layout
   */
  public Game() throws Exception {
    try {

      Gson gson = new Gson();
      Layout layout =
          gson.fromJson(JsonAccessor.getJsonFromUrl(JsonAccessor.getGivenUrl()), Layout.class);

      this.currentRoom = layout.getRoomForGivenRoomName(layout.getStartingRoom());
      this.gameLayout = layout;
      // The initial max. health of the player is given by the intial health value for the player in
      // the JSON file.
      this.playerMaxHealth = gameLayout.getPlayer().getHealth();

    } catch (JsonParseException e) {

      System.out.println("JSON formatted incorrectly.");
      System.exit(-1);
    }
  }

  /**
   * . Constructor for Game object.
   *
   * @throws Exception thrown when starting room name is not found in Room[] of Layout
   */
  public Game(String filePathName) throws Exception {

    final Path path = FileSystems.getDefault().getPath("data", filePathName);
    String fileContentAsString = "";

    try {

      fileContentAsString = new String(Files.readAllBytes(path));

    } catch (IOException e) {

      // If the file isn't found, the program lets the user know about the problem and terminates.
      System.out.println("Couldn't find file: " + filePathName);
      System.exit(-1);
    }

    try {

      Gson gson = new Gson();
      Layout layout = gson.fromJson(fileContentAsString, Layout.class);

      this.currentRoom = layout.getRoomForGivenRoomName(layout.getStartingRoom());
      this.gameLayout = layout;
      this.playerMaxHealth = gameLayout.getPlayer().getHealth();

    } catch (JsonParseException e) {

      // In case, a JsonParseException occurs, it means that the JSON file does not have the correct
      // format. Program terminates.
      System.out.println("JSON formatted incorrectly.");
      System.exit(-1);
    }
  }

  /**
   * . Constructor for Game object. Used for hardcoding for use in testing.
   *
   * @param currentRoom current room name
   * @param gameLayout the layout
   */
  public Game(Room currentRoom, Layout gameLayout, double playerMaxHealth) {
    this.currentRoom = currentRoom;
    this.gameLayout = gameLayout;
    this.playerMaxHealth = playerMaxHealth;
  }

  // Getters.
  /**
   * . Getter for current room name
   *
   * @return the current room name
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /** . Getter for current room info. Uses Room.getInformation(). Prints the current room info */
  public String getCurrentRoomInformation() {
    return currentRoom.getInformation(gameLayout);
  }

  /**
   * . Getter for current player info. Uses Layout.getPlayerInfo(). Prints the current player's
   * info.
   *
   * @return a string
   */
  public String getPlayerInfo() {
    return gameLayout.getPlayerInfo();
  }

  /**
   * . Getters for layout
   *
   * @return the layout
   */
  public Layout getGameLayout() {
    return gameLayout;
  }

  /**
   * . Function to take an item.
   *
   * @param itemName the name of the item
   * @return String indicating status
   */
  public String takeItem(String itemName) {

    // Handles the case when monsters are present in the room.
    if (currentRoom.getMonstersInRoom().size() != 0) {
      return "There are still monsters here, I can't take that.\n";
    }

    // Exceptions/edge case check.
    if (itemName == null || itemName.length() == 0) {
      return ("Your input item name is invalid\n");
    }

    Item itemToBeTaken = new Item("", 0);
    boolean itemFound = false;

    for (Item item : currentRoom.getItems()) {
      if (item.getName().equals(itemName)) {
        itemToBeTaken = item;
        itemFound = true;
      }
    }

    if (itemFound) {

      gameLayout.getPlayer().getItems().add(itemToBeTaken);
      currentRoom.getItems().remove(itemToBeTaken);
      return "";

    } else {
      return ("I can't take " + itemName + "." + "\n");
    }
  }

  /**
   * . Function to drop an item.
   *
   * @param itemName the name of the item
   * @return String indicating status
   */
  public String dropItem(String itemName) {

    // Exceptions/edge case check.
    if (itemName == null || itemName.length() == 0) {
      return ("Your input item name is invalid\n");
    }

    Item itemToBeDropped = new Item("", 0);
    boolean itemFound = false;

    for (Item item : gameLayout.getPlayer().getItems()) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        itemToBeDropped = item;
        itemFound = true;
      }
    }

    if (itemFound) {
      gameLayout.getPlayer().getItems().remove(itemToBeDropped);
      currentRoom.getItems().add(itemToBeDropped);
      // For testing:
      // return ("I have dropped " + itemName + " from " + currentRoom.getName() + ".");
      return "";

    } else {
      return ("I can't drop " + itemName + "." + "\n");
    }
  }

  /**
   * . Function that returns a String with a list of items
   *
   * @return the String of list
   */
  public String printItemsOfThePlayer() {

    StringBuilder outputStringBuilder = new StringBuilder();
    ArrayList<Item> itemsOfThePlayer = gameLayout.getPlayer().getItems();

    outputStringBuilder.append("You are carrying ");

    if (itemsOfThePlayer == null || itemsOfThePlayer.size() == 0) {

      outputStringBuilder.append("nothing\n");

    } else if (itemsOfThePlayer.size() == 1) {

      outputStringBuilder.append(itemsOfThePlayer.get(0).getName());
      outputStringBuilder.append("\n");

    } else {

      outputStringBuilder.append(itemsOfThePlayer.get(0).getName());

      for (int i = 1; i < itemsOfThePlayer.size(); i++) {
        outputStringBuilder.append(", ");
        outputStringBuilder.append(itemsOfThePlayer.get(i).getName());
      }
      outputStringBuilder.append("\n");
    }

    String itemsOfThePlayerString = outputStringBuilder.toString();
    return itemsOfThePlayerString;
  }

  /**
   * . Function that moves the current Room to a Room in the specified direction
   *
   * @return the String of status
   */
  public String moveInADirection(String directionName) throws Exception {

    // Handles the case when monsters are still present.
    if (currentRoom.getMonstersInRoom().size() != 0) {
      return "There are still monsters here, I can't move.\n";
    }

    Direction[] playerDirections = currentRoom.getDirections();

    for (Direction direction : playerDirections) {

      if (direction.getDirectionName().equalsIgnoreCase(directionName)) {
        currentRoom = gameLayout.getRoomForGivenRoomName(direction.getRoom());
        return "";
      }
    }

    return ("I can't go " + directionName + "." + "\n");
  }

  /**
   * . Function that prints the duel information
   *
   * @param monster the monster being fought
   * @return a String
   */
  public String getCurrentDuelInformation(Monster monster) {

    StringBuilder outputBuilder = new StringBuilder();

    outputBuilder.append(gameLayout.getPlayerInfo());
    outputBuilder.append("\n");
    outputBuilder.append(gameLayout.getMonsterInfo(monster));

    String duelInfo = outputBuilder.toString();
    return duelInfo;
  }

  /**
   * . Functiont hat returns the player's health and the monster's health as health bars.
   *
   * @param monster the monster being fought
   * @return a String
   */
  public String getCurrentDuelHealthBars(Monster monster) {

    // Player's health bar.
    StringBuilder playerStatus = new StringBuilder();

    double playerHealth = gameLayout.getPlayer().getHealth();
    int numberOfHashesToDisplay = 2 * (int) (10 * playerHealth / playerMaxHealth);
    int numberOfUnderscoresToDisplay = 20 - numberOfHashesToDisplay;

    playerStatus.append("Player:  |");

    for (int i = 0; i < numberOfHashesToDisplay; i++) {
      playerStatus.append("#|");
    }

    for (int i = 0; i < numberOfUnderscoresToDisplay; i++) {
      playerStatus.append(" |");
    }

    // Monster's health bar
    StringBuilder monsterStatus = new StringBuilder();

    double monsterHealth = monster.getHealth();
    int monsterNumberOfHashesToDisplay = 2 * (int) (monsterHealth / 75);
    int monsterNumberOfUnderscoresToDisplay = 20 - monsterNumberOfHashesToDisplay;

    monsterStatus.append("Monster: |");

    for (int i = 0; i < monsterNumberOfHashesToDisplay; i++) {
      monsterStatus.append("#|");
    }

    for (int i = 0; i < monsterNumberOfUnderscoresToDisplay; i++) {
      monsterStatus.append(" |");
    }

    // Joining both health bars together as one String to be returned by the function.
    StringBuilder outputBuilder = new StringBuilder();
    outputBuilder.append(playerStatus);
    outputBuilder.append("\n");
    outputBuilder.append(monsterStatus);

    String duelHealthBars = outputBuilder.toString();
    return duelHealthBars;
  }

  /**
   * Function that handles attacking a monster with an item.
   *
   * @param monster the monster being fought
   * @param itemName the item being used to fight
   * @return a String
   */
  public String attackMonsterWithItem(Monster monster, String itemName) {

    boolean itemFound = false;

    Item itemBeingUsed = new Item("", 0);

    for (Item item : gameLayout.getPlayer().getItems()) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        itemBeingUsed = item;
        itemFound = true;
      }
    }

    // Handles case when item of itemName isn't found in the player's list of items.
    if (!itemFound) {
      return "Item not found.";
    }

    // Monster Damage calculated using formula provided
    double monsterDamage =
        gameLayout.getPlayer().getAttack() + itemBeingUsed.getDamage() - monster.getDefense();

    // Handles case when damage is negative.
    // Since health can't increase in a duel, all negative damage is changed to zero damage.
    if (monsterDamage < 0) {
      monsterDamage = 0;
    }

    double newMonsterHealth = monster.getHealth() - monsterDamage;
    monster.setHealth(newMonsterHealth);

    // Negative monster health value is considered as the monster being killed.
    if (monster.getHealth() <= 0) {
      getCurrentRoom().getMonstersInRoom().remove(monster.getName());
      // My formula divides by 2 instead of 20 because
      // my JSON has large attack-defense-health values.
      experience += ((monster.getAttack() + monster.getDefense()) / 2 + monsterMaxHealth) * 2;
      levelUpdate();
      return "Monster is dead. You win this duel.";
    }

    // Player Damage calculated using formula provided
    double playerDamage = monster.getAttack() - gameLayout.getPlayer().getDefense();

    // Handles case when damage is negative.
    // Since health can't increase in a duel, all negative damage is changed to zero damage.
    if (playerDamage < 0) {
      playerDamage = 0;
    }

    double newPlayerHealth = gameLayout.getPlayer().getHealth() - playerDamage;
    gameLayout.getPlayer().setHealth(newPlayerHealth);

    // Negative player health value means that the player has died. The game ends here, and the
    // program terminates.
    if (gameLayout.getPlayer().getHealth() <= 0) {
      System.out.println("You have died. GAME OVER! BUHAHAHAHAHAHAHAHAHAHA");
      System.exit(0);
    }

    return "";
  }

  /**
   * . Function that handles attacking a monster
   *
   * @param monster the monster being fought
   * @return a String
   */
  public String attackMonster(Monster monster) {

    // Monster Damage calculated using formula provided
    double monsterDamage = gameLayout.getPlayer().getAttack() - monster.getDefense();

    // Handles case when damage is negative.
    // Since health can't increase in a duel, all negative damage is changed to zero damage.
    if (monsterDamage < 0) {
      monsterDamage = 0;
    }

    double newMonsterHealth = monster.getHealth() - monsterDamage;
    monster.setHealth(newMonsterHealth);

    // Negative monster health value is considered as the monster being killed.
    if (monster.getHealth() <= 0) {
      getCurrentRoom().getMonstersInRoom().remove(monster.getName());
      // My formula divides by 2 instead of 20 because
      // my JSON has large attack-defense-health values
      experience += ((monster.getAttack() + monster.getDefense()) / 2 + monsterMaxHealth) * 2;
      levelUpdate();
      return "Monster is dead. You win this duel.";
    }

    // Player Damage calculated using formula provided
    double playerDamage = monster.getAttack() - gameLayout.getPlayer().getDefense();

    // Handles case when damage is negative.
    // Since health can't increase in a duel, all negative damage is changed to zero damage.
    if (playerDamage < 0) {
      playerDamage = 0;
    }

    double newPlayerHealth = gameLayout.getPlayer().getHealth() - playerDamage;
    gameLayout.getPlayer().setHealth(newPlayerHealth);

    // Negative player health value means that the player has died. The game ends here, and the
    // program terminates.
    if (gameLayout.getPlayer().getHealth() <= 0) {
      System.out.println("You have died. GAME OVER!");
      System.exit(0);
    }

    return "";
  }

  /** . Function that executes a level update */
  public void levelUpdate() {

    // Finds the level for the current experience value.
    int level;
    for (level = 1; level > 0; level++) {
      double levelThreshold = requiredExperienceForLevel(level);
      if (experience < levelThreshold) {
        level--;
        break;
      }
    }

    // Update player's attributes for the level.
    Player player = getGameLayout().getPlayer();
    if (level > player.getLevel()) {
      player.setLevel(level);
      player.setAttack(1.5 * player.getAttack());
      player.setDefense(1.5 * player.getDefense());
      playerMaxHealth *= 1.3;
      player.setHealth(playerMaxHealth);
    }
  }

  /**
   * . Recursive function that calculates experience for a level
   *
   * @param level the level number
   * @return the experience threshold for the level
   */
  public static double requiredExperienceForLevel(int level) {
    // My values for Level 1 and 2 are 250 and 500, instead of 25 and 50, because my
    // JSON has large values for player's and monster's attack-defense-health values.
    if (level == 1) {
      return 250;
    }
    if (level == 2) {
      return 500;
    }
    return (requiredExperienceForLevel(level - 1) + requiredExperienceForLevel(level - 2)) * 1.1;
  }

  // .equals() method overriden to declare equality by comparing values
  /**
   * . Auto-generated function checking for equality between objects
   *
   * @param o the object to be compared with
   * @return boolean value
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(currentRoom, game.currentRoom)
        && Objects.equals(gameLayout, game.gameLayout)
        && Double.compare(this.playerMaxHealth, game.playerMaxHealth) == 0;
  }
}
