import java.util.Scanner;

/*
   CS 126 Assignment 4: Adventure II
   Author: Dipro Ray
   Github Link: www.github.com/uiuc-sp18-cs126/adventure-diproray
   Publicly - Available Tools Used:
   - Google's gson Library
   Publicly - Available Plugins for Pretty Printing and Code Formatting Used:
   - google-java-reformat Plugin for IntelliJ (https://github.com/google/google-java-format)
   - checkstyle Plugin for Google Style Guide (https://github.com/checkstyle/checkstyle)
*/

/**
 * . Class modelling the User Interface for the game. Note that this Interface is independent of the
 * Game to support modularity and allow customization of Interface to programmers' preferences.
 *
 * @author diproray
 */
public class Interface {

  // The Game of the game.
  private static Game game;

  // String Constants:
  // Each String Constant stands to identify each of the possible commands to the Adventure game,
  // based on provided specifications.

  private static final String QUIT_COMMAND = "quit";
  private static final String EXIT_COMMAND = "exit";

  private static final String PLAYER_INFO_COMMAND = "playerinfo";

  private static final String DUEL_COMMAND = "duel";
  private static final String DISENGAGE_COMMAND = "disengage";
  private static final String STATUS_COMMAND = "status";
  private static final String ATTACK_COMMAND = "attack";
  private static final String ATTACK_WITH_COMMAND = "attack with";

  private static final String GO_COMMAND = "go ";
  private static final String RUN_COMMAND = "run ";
  private static final String WALK_COMMAND = "walk ";

  private static final String LIST_COMMAND = "list";
  private static final String ITEMS_COMMAND = "items";

  private static final String TAKE_ITEM_COMMAND = "take ";
  private static final String COLLECT_ITEM_COMMAND = "collect ";
  private static final String CARRY_ITEM_COMMAND = "carry ";
  private static final String BORROW_ITEM_COMMAND = "borrow ";

  private static final String DROP_ITEM_COMMAND = "drop ";
  private static final String LEAVE_ITEM_COMMAND = "leave ";
  private static final String KEEP_ITEM_COMMAND = "keep ";

  private static final String I_DONT_UNDERSTAND = "I don't understand ";

  private static Scanner scan = new Scanner(System.in);

  /**
   * . the main function
   *
   * @param args command - line arguments
   */
  public static void main(String[] args) throws Exception {

    initiateGamePlayer(args);

    validateGameLayout();

    // Loop runs endlessly till the program ends.
    do {

      System.out.println(game.getCurrentRoomInformation());

      // The program terminates if the player reaches the ending room.
      if (game.getCurrentRoom().getName().equals(game.getGameLayout().getEndingRoom())) {
        System.exit(0);
      }

      String command = scan.nextLine();
      String commandLowerCase = command.toLowerCase();

      // If the following code inside the try{} block doesn't execute, then the command is unknown
      // and, possibly, causes unexpected exceptions. This is handles by the catch{} block.
      try {

        // If the command is Quit or Exit, the game ends.
        if (commandLowerCase.equalsIgnoreCase(QUIT_COMMAND)
            || commandLowerCase.equalsIgnoreCase(EXIT_COMMAND)) {
          System.exit(0);
        }

        // NOTE: If a command is identified, the code within the block is executed,
        // and we skip to the next iteration (because the command can only be of one command type).

        // If the command is Player Info, the player info is displayed.
        if (commandLowerCase.equalsIgnoreCase(PLAYER_INFO_COMMAND)) {
          System.out.println(game.getPlayerInfo());
          continue;
        }

        // If the command is duel monsterName, the duelWithAMonster() function is called.
        if (commandLowerCase.startsWith(DUEL_COMMAND)) {

          String monster = command.substring(command.indexOf(' ') + 1);

          duelWithAMonster(monster);
          continue;
        }

        // If the command is of the form "go aDirection", the game tries moving the player in that
        // direction.
        if (commandLowerCase.startsWith(GO_COMMAND)
            || commandLowerCase.startsWith(RUN_COMMAND)
            || commandLowerCase.startsWith(WALK_COMMAND)) {

          String direction = command.substring(command.indexOf(' ') + 1);

          System.out.print(game.moveInADirection(direction));
          continue;
        }

        // If the command is "list", the game list the players items.
        if (commandLowerCase.startsWith(LIST_COMMAND)
            || commandLowerCase.startsWith(ITEMS_COMMAND)) {
          System.out.print(game.printItemsOfThePlayer());
          continue;
        }

        // If the command is of the form "take anItem", the game tries making the player take the
        // item.
        if (commandLowerCase.startsWith(TAKE_ITEM_COMMAND)
            || commandLowerCase.startsWith(COLLECT_ITEM_COMMAND)
            || commandLowerCase.startsWith(CARRY_ITEM_COMMAND)
            || commandLowerCase.startsWith(BORROW_ITEM_COMMAND)) {

          String item = command.substring(command.indexOf(' ') + 1);

          System.out.print(game.takeItem(item));
          continue;
        }

        // If the command is of the form "drop anItem", the game tries making the player drop the
        // item.
        if (commandLowerCase.startsWith(DROP_ITEM_COMMAND)
            || commandLowerCase.startsWith(LEAVE_ITEM_COMMAND)
            || commandLowerCase.startsWith(KEEP_ITEM_COMMAND)) {

          String item = command.substring(command.indexOf(' ') + 1);

          System.out.print(game.dropItem(item));
          continue;
        }

        // If the command matches none of the above, it is unknown.
        System.out.print(I_DONT_UNDERSTAND + "'" + command + "'" + "\n");

      } catch (Exception e) {

        // If any exception occurs, the command isn't valid, so it is unknown.
        System.out.print(I_DONT_UNDERSTAND + "'" + command + "'" + "\n");
      }

    } while (true);
  }

  /**
   * Initializes a new Game using JSON data pulled from URL through JsonAccessor.java
   *
   * @throws Exception when startingRoom name inside JSON is invalid. This error will not be
   *     encountered as long as JSON format and scheme are correct.
   */
  private static void initiateGameFromSpecifiedUrl() throws Exception {
    game = new Game();
  }

  /**
   * Initializes a new Game using JSON data pulled from local "data" folder.
   *
   * @throws Exception when startingRoom name inside JSON is invalid. This error will not be
   *     encountered as long as JSON format and scheme are correct.
   */
  private static void initiateGameFromSpecifiedFilePath(String filePathName) throws Exception {
    game = new Game(filePathName);
  }

  /**
   * . Loads JSON data into a Layout and creates a Game Game.
   *
   * @param args the command line argument passed in
   * @throws Exception Exception thrown when JSON file has invalid starting room name.
   */
  private static void initiateGamePlayer(String[] args) throws Exception {

    // Uses command line argument to get file path of the local JSON file containing the game
    // layout.
    // If no command line argument is specified (i.e. the code results in
    // IndexOutOfBoundsException), the default URL is used to get the JSON data.
    // NOTE:
    // In case of any exception other than IndexOutOfBoundsException while executing
    // initiateGameFromSpecifiedFilePath(args[0]), the default URL is NOT used to get JSON data.
    // Instead, the exception is returned. (A possible example of such an exception is when the File
    // name isn't found in the data directory.)

    try {

      initiateGameFromSpecifiedFilePath(args[0]);
      System.out.println("Game has loaded from local JSON file.\n");

    } catch (IndexOutOfBoundsException e) {

      initiateGameFromSpecifiedUrl();
      System.out.println("Game has loaded from JSON extracted from URL.\n");
    }
  }

  /**
   * . Function checks if the game Layout is valid.
   *
   * @throws Exception Exception thrown when JSON file has invalid starting room name.
   */
  private static void validateGameLayout() throws Exception {

    // Validator.mapValidatorWrapper() (for Extra Credit) function checks
    // whether a path exists between starting room and ending room.
    // Validator.floorPlanValidator() (for Extra Credit) function checks
    // whether for each room B connected to room A, room A is connected to room B.
    // Validator.mapValidatorWrapper() and Validator.floorPlanValidator()
    // return an empty string when the JSON file is valid.
    // If the string returned are not empty, the file is invalid, an error message is displayed, and
    // the program terminates.

    String pathValidationStatusString = Validator.mapValidatorWrapper(game.getGameLayout());
    String floorPlanValidationStatusString = Validator.floorPlanValidator(game.getGameLayout());

    System.out.println(floorPlanValidationStatusString);
    if (floorPlanValidationStatusString.length() != 0) {
      System.exit(-1);
    }

    System.out.println(pathValidationStatusString);
    if (pathValidationStatusString.length() != 0) {
      System.exit(-1);
    }
  }

  /**
   * Function that controls the interface when the player duels with a monster.
   *
   * @param monster the monster being fought
   */
  private static void duelWithAMonster(String monster) {

    boolean monsterFound = false;

    for (String monsterName : game.getCurrentRoom().getMonstersInRoom()) {
      if (monster.equalsIgnoreCase(monsterName)) {
        monsterFound = true;
        break;
      }
    }

    // Handles case when monster of monsterName is not found in the room.
    if (!monsterFound) {
      System.out.println("I can't duel " + monster + ".\n");
      return;
    }

    Monster monsterBeingFought = game.getGameLayout().getMonsterForGivenMonsterName(monster);

    // Loop runs endlessly till the function ends.

    while (true) {

      // If the monster being fought has negative value, it is considered to be dead.
      // The duel ends.
      if (monsterBeingFought.getHealth() <= 0) {
        System.out.println("You have defeated the monster.");
        break;
      }

      System.out.println(
          "DUEL STATUS:" + "\n" + game.getCurrentDuelInformation(monsterBeingFought));

      String command = scan.nextLine();
      String commandLowerCase = command.toLowerCase();

      // If the following code inside the try{} block doesn't execute, then the command is unknown
      // and, possibly, causes unexpected exceptions. This is handles by the catch{} block.
      try {

        // If the command is Quit or Exit, the duel ends.
        if (commandLowerCase.equalsIgnoreCase(QUIT_COMMAND)
            || commandLowerCase.equalsIgnoreCase(EXIT_COMMAND)) {
          System.exit(0);
        }

        // If the command is disengage, the duel ends.
        if (commandLowerCase.equalsIgnoreCase(DISENGAGE_COMMAND)) {
          break;
        }

        // NOTE: If a command is identified, the code within the block is executed,
        // and we skip to the next iteration (because the command can only be of one command type).

        // If the command is Player Info, the player info is displayed.
        if (commandLowerCase.equalsIgnoreCase(PLAYER_INFO_COMMAND)) {
          System.out.println(game.getPlayerInfo());
          continue;
        }

        // If the command is status, the player's health and monster's health
        // are displayed as health bars.
        if (commandLowerCase.equalsIgnoreCase(STATUS_COMMAND)) {
          System.out.println(game.getCurrentDuelHealthBars(monsterBeingFought));
          continue;
        }

        // If the command is attack with itemName, the monster is attacked with that item.
        if (commandLowerCase.startsWith(ATTACK_WITH_COMMAND)) {
          String item = command.substring(12);
          System.out.println(game.attackMonsterWithItem(monsterBeingFought, item));
          continue;
        }

        // If the command is attack, the monster is attacked.
        if (commandLowerCase.equalsIgnoreCase(ATTACK_COMMAND)) {
          System.out.println(game.attackMonster(monsterBeingFought));
          continue;
        }

        // If the command is "list", the game list the player's items.
        if (commandLowerCase.startsWith(LIST_COMMAND)
            || commandLowerCase.startsWith(ITEMS_COMMAND)) {
          System.out.print(game.printItemsOfThePlayer());
          continue;
        }

        // If the command matches none of the above, it is unknown.
        System.out.print(I_DONT_UNDERSTAND + "'" + command + "'" + "\n");

      } catch (Exception e) {

        // If any exception occurs, the command isn't valid, so it is unknown.
        System.out.print(I_DONT_UNDERSTAND + "'" + command + "'" + "\n");
      }
    }
  }
}
