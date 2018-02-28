import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * . Class that models a Room object
 *
 * @author diproray
 */
public class Room {

  // Member variables.
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String name;
  private String description;
  private Direction[] directions;
  private ArrayList<Item> items;
  private ArrayList<String> monstersInRoom;

  // Constructor.

  /**
   * . Constructor for a Room object.
   *
   * @param name the room's name
   * @param description the description of the room
   * @param directions the directions possible
   * @param items the items in the room
   */
  public Room(
      String name,
      String description,
      Direction[] directions,
      ArrayList<Item> items,
      ArrayList<String> monstersInRoom) {
    this.name = name;
    this.description = description;
    this.directions = directions;
    this.items = items;
    this.monstersInRoom = monstersInRoom;
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
    Room room = (Room) o;
    return Objects.equals(name, room.name)
        && Objects.equals(description, room.description)
        && Arrays.equals(directions, room.directions)
        && Objects.equals(items, room.items)
        && Objects.equals(monstersInRoom, room.monstersInRoom);
  }

  // Getters.

  /**
   * . Getter for name
   *
   * @return the room's name
   */
  public String getName() {

    return name;
  }

  /**
   * . Getter for description
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * . Getter for directions
   *
   * @return the directions
   */
  public Direction[] getDirections() {
    return directions;
  }

  /**
   * . Getter for items
   *
   * @return the items
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * . Getter for monsters names
   *
   * @return the monster names
   */
  public ArrayList<String> getMonstersInRoom() {
    return monstersInRoom;
  }

  /**
   * . Function that returns a String containing the room's information. NOTE: Exceptions do not
   * need to be handled here, because Layout here is always valid, as JSON is valid (or gson would
   * give exception).
   *
   * @param layout the layout
   * @return room's information
   */
  public String getInformation(Layout layout) {

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append(this.getDescription());
    outputStringBuilder.append("\n");
    outputStringBuilder.append(getStringWithStartingOrEndingRoomInformation(layout));
    outputStringBuilder.append(getStringWithInformationRegardingItems());
    outputStringBuilder.append(getStringWithInformationRegardingMonsters());

    if (monstersInRoom.size() == 0) {
      outputStringBuilder.append(getStringWithInformationRegardingDirections());
    }

    String roomInformation = outputStringBuilder.toString();
    return roomInformation;
  }

  /**
   * . Returns a list of items in the room
   *
   * @return a String containing a list of all items
   */
  public String getStringWithInformationRegardingItems() {

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append("This room contains ");

    if (this.items == null || this.items.size() == 0) {
      outputStringBuilder.append("nothing\n");
    } else if (this.items.size() == 1) {
      outputStringBuilder.append(items.get(0).getName());
      outputStringBuilder.append("\n");
    } else {
      outputStringBuilder.append(items.get(0).getName());
      for (int i = 1; i < items.size(); i++) {
        outputStringBuilder.append(", ");
        outputStringBuilder.append(items.get(i).getName());
      }
      outputStringBuilder.append("\n");
    }

    String itemsInformation = outputStringBuilder.toString();
    return itemsInformation;
  }

  /**
   * . Returns a list of monsters in the room
   *
   * @return a String containing a list of all monsters
   */
  public String getStringWithInformationRegardingMonsters() {

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append("This room has the following monsters: ");

    if (this.monstersInRoom == null || this.monstersInRoom.size() == 0) {
      outputStringBuilder.append("no monsters\n");
    } else if (this.monstersInRoom.size() == 1) {
      outputStringBuilder.append(monstersInRoom.get(0));
      outputStringBuilder.append("\n");
    } else {
      outputStringBuilder.append(monstersInRoom.get(0));
      for (int i = 1; i < monstersInRoom.size(); i++) {
        outputStringBuilder.append(", ");
        outputStringBuilder.append(monstersInRoom.get(i));
      }
      outputStringBuilder.append("\n");
    }

    String monstersInformation = outputStringBuilder.toString();
    return monstersInformation;
  }

  /**
   * . Returns a list of directions to move in from the room
   *
   * @return a String containing a list of all directions
   */
  public String getStringWithInformationRegardingDirections() {

    StringBuilder outputStringBuilder = new StringBuilder();

    outputStringBuilder.append("From here, you can go: ");

    if (this.directions == null || this.directions.length == 0) {
      outputStringBuilder.append("nowhere");
    } else if (this.directions.length == 1) {
      outputStringBuilder.append(directions[0].getDirectionName());
    } else {
      outputStringBuilder.append(directions[0].getDirectionName());
      for (int i = 1; i < directions.length - 1; i++) {
        outputStringBuilder.append(", ");
        outputStringBuilder.append(directions[i].getDirectionName());
      }
      outputStringBuilder.append(", or ");
      outputStringBuilder.append(directions[directions.length - 1].getDirectionName());
    }

    String directionsInformation = outputStringBuilder.toString();
    return directionsInformation;
  }

  /**
   * . Function that returns a String containing "Your journey begins here" or "You have reached
   * your final destination" depending on if the room is a Starting Room or an Ending Room, or "" if
   * neither.
   *
   * @param layout the layout
   * @return a String
   */
  private String getStringWithStartingOrEndingRoomInformation(Layout layout) {

    StringBuilder outputStringBuilder = new StringBuilder();

    if (this.getName().equals(layout.getStartingRoom())) {
      outputStringBuilder.append("Your journey begins here");
      outputStringBuilder.append("\n");
    }
    if (this.getName().equals(layout.getEndingRoom())) {
      outputStringBuilder.append("You have reached your final destination");
      outputStringBuilder.append("\n");
    }

    String startingOrEndingRoomInformation = outputStringBuilder.toString();
    return startingOrEndingRoomInformation;
  }
}
