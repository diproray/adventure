import java.util.ArrayList;

/**
 * . Class containing layout validation functions
 *
 * @author diproray
 */
public class Validator {

  private static ArrayList<String> visitedRoomsNames = new ArrayList<>();

  /**
   * . Wrapper function for recursive mapValidator
   *
   * @param layout the game layout
   * @return the String containing validation status
   * @throws Exception when JSON file is incorrectly formatted
   */
  public static String mapValidatorWrapper(Layout layout) throws Exception {

    String output = "";

    Room room = layout.getRoomForGivenRoomName(layout.getStartingRoom());
    Room endingRoom = layout.getRoomForGivenRoomName(layout.getEndingRoom());

    boolean startToEndPathExists = mapValidatorRecursive(room, endingRoom, layout);

    if (!startToEndPathExists) {
      output =
          "The layout JSON is not valid. The ending room "
              + layout.getEndingRoom()
              + " cannot be reached from the starting room "
              + layout.getStartingRoom()
              + ".";
    }

    return output;
  }

  /**
   * . Recursive method for checking path between starting room and ending room.
   *
   * @param room the room you're at
   * @param endingRoom the ending room
   * @param layout the game layout
   * @return a boolean value
   * @throws Exception when JSON is incorrectly formatted
   */
  private static boolean mapValidatorRecursive(Room room, Room endingRoom, Layout layout)
      throws Exception {

    visitedRoomsNames.add(room.getName());

    if (room.getName().equals(endingRoom.getName())) {
      return true;
    }

    ArrayList<Room> connectedRooms = Validator.getConnectedRoomsList(room, layout);

    for (Room connectedRoom : connectedRooms) {

      if (visitedRoomsNames.contains(connectedRoom.getName())) {
        continue;
      }

      boolean pathFound = mapValidatorRecursive(connectedRoom, endingRoom, layout);

      if (pathFound) {
        return true;
      }
    }

    return false;
  }

  /**
   * . Helper function that returns an ArrayList of type Room of all rooms connected to a particular
   * room
   *
   * @param room the room
   * @param layout the layout
   * @return an ArrayList<Room>
   * @throws Exception throws an exception when JSON is incorrectly formatted
   */
  private static ArrayList<Room> getConnectedRoomsList(Room room, Layout layout) throws Exception {
    ArrayList<Room> connectedRooms = new ArrayList<>();
    for (Direction direction : room.getDirections()) {
      connectedRooms.add(layout.getRoomForGivenRoomName(direction.getRoom()));
    }
    return connectedRooms;
  }

  /**
   * . Function that checks if for every Room B directly connected to Room A, Room A is connected to
   * Room B directly
   *
   * @param layout the layout
   * @return a String with validation status
   * @throws Exception when JSON is incorrectly formatted
   */
  public static String floorPlanValidator(Layout layout) throws Exception {
    Room[] roomsInLayout = layout.getRooms();
    for (Room room : roomsInLayout) {
      ArrayList<Room> connectedRooms = Validator.getConnectedRoomsList(room, layout);
      for (Room connectedRoom : connectedRooms) {
        if (!Validator.getConnectedRoomsList(connectedRoom, layout).contains(room)) {
          return "Your floor plan is invalid.";
        }
      }
    }
    return "";
  }
}
