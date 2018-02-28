import java.util.Objects;

/**
 * . Class modelling a Direction JSON object.
 *
 * @author diproray
 */
public class Direction {

  // Member variables:
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String directionName;
  private String room;

  // Constructor.
  /**
   * . Constructor for a Direction object. Not currently used in the program, but is used for
   * testing purposes.
   *
   * @param directionName the direction name
   * @param room the room
   */
  public Direction(String directionName, String room) {
    this.directionName = directionName;
    this.room = room;
  }

  // Getters.
  /**
   * . Getter for directionName
   *
   * @return the direction name
   */
  public String getDirectionName() {
    return directionName;
  }

  /**
   * . Getter for room
   *
   * @return the room
   */
  public String getRoom() {
    return room;
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
    Direction direction = (Direction) o;
    return Objects.equals(directionName, direction.directionName)
        && Objects.equals(room, direction.room);
  }
}
