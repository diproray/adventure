import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * . Class for testing Layout.java
 *
 * @author diproray
 */
public class LayoutTest {

  /** Tests that objects were correctly modelled to represet the JSON objects for the game. */
  @Test
  public void checkForCorrectObjectModelling() {
    Gson gson = new Gson();
    Layout layout = gson.fromJson(Sample.SAMPLE_TESTING_JSON_STRING, Layout.class);
    assertEquals(Sample.SAMPLE_LAYOUT_READ_ONLY, layout);
  }

  @Test
  public void getRoomForGivenRoomName() throws Exception {
    assertEquals(
        Sample.ROOM_ONE,
        Sample.SAMPLE_LAYOUT.getRoomForGivenRoomName(Sample.SAMPLE_LAYOUT.getStartingRoom()));
  }

  @Test
  public void getRooms() {
    assertArrayEquals(
        new Room[] {Sample.ROOM_ONE, Sample.ROOM_TWO}, Sample.SAMPLE_LAYOUT.getRooms());
  }

  @Test
  public void getEndingRoom() {
    assertEquals(Sample.ROOM_TWO.getName(), Sample.SAMPLE_LAYOUT.getEndingRoom());
  }

  @Test
  public void getRoomForGivenRoomNameNull() {
    try {
      Sample.SAMPLE_GAME.getGameLayout().getRoomForGivenRoomName(null);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "Invalid room name.");
    }
  }

  @Test
  public void getRoomForGivenRoomNameNotFound() {
    try {
      Sample.SAMPLE_GAME.getGameLayout().getRoomForGivenRoomName("Siebel1112");
    } catch (Exception e) {
      assertEquals(e.getMessage(), "Room doesn't exist.");
    }
  }

  @Test
  public void getPlayerInfo() {
    assertEquals(
        "Name: Dipro Ray; "
            + "Level: 1; "
            + "Attack: 350.0; "
            + "Defense: 450.0; "
            + "Health: 1000.0",
        Sample.SAMPLE_LAYOUT_READ_ONLY.getPlayerInfo());
  }

  @Test
  public void getMonsterForGivenMonsterName() {
    assertEquals(
        Sample.MONSTER_ONE,
        Sample.SAMPLE_LAYOUT_READ_ONLY.getMonsterForGivenMonsterName("Cookie Monster"));
  }

  @Test
  public void getPlayer() {
    assertEquals(Sample.PLAYER_ONE_READ_ONLY, Sample.SAMPLE_LAYOUT_READ_ONLY.getPlayer());
  }

  @Test
  public void getMonsters() {
    assertArrayEquals(
        new Monster[] {Sample.MONSTER_ONE_READ_ONLY, Sample.MONSTER_TWO_READ_ONLY},
        Sample.SAMPLE_LAYOUT_READ_ONLY.getMonsters());
  }

  @Test
  public void getMonsterInfo() {
    assertEquals(
        "Name: Cookie Monster; Attack: 250.0; Defense: 150.0; Health: 750.0",
        Sample.SAMPLE_LAYOUT_READ_ONLY.getMonsterInfo(Sample.MONSTER_ONE_READ_ONLY));
  }

  @Test
  public void getMonsterInfoForNonExistentMonster() {
    assertEquals(
        "Monster not found.",
        Sample.SAMPLE_LAYOUT_READ_ONLY.getMonsterInfo(new Monster("zombie", 100, 200, 300)));
  }

  @Test
  public void equalsDifferentObjectWithSameValues() {
    assertTrue(
        Sample.SAMPLE_LAYOUT_READ_ONLY.equals(
            new Layout(
                "MatthewsStreet",
                "Siebel1314",
                new Room[] {Sample.ROOM_ONE_READ_ONLY, Sample.ROOM_TWO_READ_ONLY},
                Sample.PLAYER_ONE_READ_ONLY,
                new Monster[] {Sample.MONSTER_ONE_READ_ONLY, Sample.MONSTER_TWO_READ_ONLY})));
  }

  @Test
  public void equalsNull() {
    assertFalse(Sample.SAMPLE_LAYOUT_READ_ONLY.equals(null));
  }

  @Test
  public void equalsSameObject() {
    assertTrue(Sample.SAMPLE_LAYOUT_READ_ONLY.equals(Sample.SAMPLE_LAYOUT_READ_ONLY));
  }

  @Test
  public void getMonsterForGivenMonsterNameNull() {
    try {
      Sample.SAMPLE_LAYOUT_READ_ONLY.getMonsterForGivenMonsterName(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid monster name.", e.getMessage());
    }
  }
}
