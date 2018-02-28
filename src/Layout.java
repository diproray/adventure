import java.util.Arrays;
import java.util.Objects;

/**
 * . Class modelling a Layout JSON object.
 *
 * @author diproray
 */
public class Layout {

  // Member variables:
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String startingRoom;
  private String endingRoom;
  private Room[] rooms;
  private Player player;
  private Monster[] monsters;

  // Constructors.
  /**
   * . Constructor for a Layout object.
   *
   * @param startingRoom the starting room
   * @param endingRoom the ending room
   * @param rooms the array of rooms
   */
  public Layout(
      String startingRoom, String endingRoom, Room[] rooms, Player player, Monster[] monsters) {

    this.startingRoom = startingRoom;
    this.endingRoom = endingRoom;
    this.rooms = rooms;
    this.player = player;
    this.monsters = monsters;
  }

  // Getters.
  /**
   * . Getter for startingRoom
   *
   * @return the starting room
   */
  public String getStartingRoom() {
    return startingRoom;
  }

  /**
   * . getter for player
   *
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * . Getter for monsters array
   *
   * @return an array of Monsters
   */
  public Monster[] getMonsters() {
    return monsters;
  }

  /**
   * . Getter for endingRoom
   *
   * @return the ending room
   */
  public String getEndingRoom() {
    return endingRoom;
  }

  /**
   * . Getters for array of rooms.
   *
   * @return the array of rooms
   */
  public Room[] getRooms() {
    return rooms;
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

    Layout layout = (Layout) o;

    return Objects.equals(startingRoom, layout.startingRoom)
        && Objects.equals(endingRoom, layout.endingRoom)
        && Arrays.equals(rooms, layout.rooms)
        && Objects.equals(player, layout.player)
        && Arrays.equals(monsters, layout.monsters);
  }

  // Utility functions.

  /**
   * . Function that returns the required Room object when provided with the room name.
   *
   * @param roomName the room's name
   * @return a Room object
   * @throws Exception when room doesn't exist
   * @throws IllegalArgumentException when string is invalid. This depends on the JSON data. User
   *     input does not affect it.
   */
  public Room getRoomForGivenRoomName(String roomName) throws Exception {

    // Exception/edge case check.
    if (roomName == null || roomName.length() == 0) {
      throw new IllegalArgumentException("Invalid room name.");
    }

    boolean roomFound = false;
    Room requiredRoom = new Room("", "", null, null, null);

    for (Room room : rooms) {
      if (roomName.equals(room.getName())) {
        requiredRoom = room;
        roomFound = true;
      }
    }

    if (!roomFound) {
      throw new Exception("Room doesn't exist.");
    }

    return requiredRoom;
  }

  /**
   * . Function that returns the required Monster object when provided with the monster name.
   *
   * @param monsterName the monster name for which the Monster objet is to be found
   * @return a Monster object
   */
  public Monster getMonsterForGivenMonsterName(String monsterName) {

    // Exception/edge case check.
    if (monsterName == null || monsterName.length() == 0) {
      throw new IllegalArgumentException("Invalid monster name.");
    }

    boolean monsterFound = false;
    Monster requiredMonster = new Monster("", 0, 0, 0);

    for (Monster monster : monsters) {
      if (monster.getName().equalsIgnoreCase(monsterName)) {
        requiredMonster = monster;
        monsterFound = true;
      }
    }

    if (!monsterFound) {
      System.out.println("Monster doesn't exist.");
      System.exit(-1);
    }

    return requiredMonster;
  }

  /**
   * . Function that returns player info
   *
   * @return a String
   */
  public String getPlayerInfo() {

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append("Name: ");
    outputStringBuilder.append(player.getName());

    outputStringBuilder.append("; Level: ");
    outputStringBuilder.append(player.getLevel());

    outputStringBuilder.append("; Attack: ");
    outputStringBuilder.append(player.getAttack());

    outputStringBuilder.append("; Defense: ");
    outputStringBuilder.append(player.getDefense());

    outputStringBuilder.append("; Health: ");
    outputStringBuilder.append(player.getHealth());

    String playerInfo = outputStringBuilder.toString();
    return playerInfo;
  }

  /**
   * . Function that returns monster info
   *
   * @param monster the monster whose info is required
   * @return a Stirng
   */
  public String getMonsterInfo(Monster monster) {

    if (!Arrays.asList(monsters).contains(monster)) {
      return "Monster not found.";
    }

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append("Name: ");
    outputStringBuilder.append(monster.getName());

    outputStringBuilder.append("; Attack: ");
    outputStringBuilder.append(monster.getAttack());

    outputStringBuilder.append("; Defense: ");
    outputStringBuilder.append(monster.getDefense());

    outputStringBuilder.append("; Health: ");
    outputStringBuilder.append(monster.getHealth());

    String monsterInfo = outputStringBuilder.toString();
    return monsterInfo;
  }
}
