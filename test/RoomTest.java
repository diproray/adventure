import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RoomTest {

  Room room =
      new Room(
          "Temporary",
          "Temporary room",
          null,
          new ArrayList<Item>(
              Arrays.asList(
                  new Item("item1", 100), new Item("item2", 100), new Item("item3", 100))),
          new ArrayList<String>(Arrays.asList("Zombie1", "Zombie2")));

  Room room2 =
      new Room(
          "Temporary",
          "Temporary room",
          new Direction[] {new Direction("north", "room1")},
          null,
          new ArrayList<String>(Arrays.asList("Zombie1", "Zombie2")));

  @Test
  public void getInformationStartingRoom() {
    assertEquals(
        "You are on Matthews, outside the Siebel Center\n"
            + "Your journey begins here\n"
            + "This room contains coin\n"
            + "This room has the following monsters: Cookie Monster\n",
        Sample.ROOM_ONE_READ_ONLY.getInformation(Sample.SAMPLE_LAYOUT_TWO));
  }

  @Test
  public void getInformationEndingRoom() {
    assertEquals(
        "You are in Siebel 1314.  There are happy CS 126 students doing a code review.\n"
            + "You have reached your final destination\n"
            + "This room contains bazooka\n"
            + "This room has the following monsters: Loch Ness Monster\n",
        Sample.ROOM_TWO.getInformation(Sample.SAMPLE_LAYOUT_TWO));
  }

  @Test
  public void getInformationOneItemAndOneMonster() {
    assertEquals(
        "You are on Matthews, outside the Siebel Center\n"
            + "Your journey begins here\n"
            + "This room contains coin\n"
            + "This room has the following monsters: Cookie Monster\n",
        Sample.ROOM_ONE_READ_ONLY.getInformation(Sample.SAMPLE_LAYOUT));
  }

  @Test
  public void getInformationNoItemsNoDirectionsNoMonsters() {
    assertEquals(
        "You are on Matthews, outside the Siebel Center\n"
            + "Your journey begins here\n"
            + "This room contains nothing\n"
            + "This room has the following monsters: no monsters\n"
            + "From here, you can go: nowhere",
        Sample.ROOM_FIVE.getInformation(Sample.SAMPLE_LAYOUT));
  }

  @Test
  public void getInformationMoreThanTwoDirectionsAndNoMonsters() {
    assertEquals(
        "You are on Matthews, outside the Siebel Center\n"
            + "Your journey begins here\n"
            + "This room contains nothing\n"
            + "This room has the following monsters: no monsters\n"
            + "From here, you can go: East, West, or East",
        Sample.ROOM_SIX.getInformation(Sample.SAMPLE_LAYOUT));
  }

  @Test
  public void equalsNull() {
    assertFalse(Sample.ROOM_TWO.equals(null));
  }

  @Test
  public void getStringWithInformationRegardingTwoMonsters() {
    assertEquals(
        "This room has the following monsters: Zombie1, Zombie2\n",
        room.getStringWithInformationRegardingMonsters());
  }

  @Test
  public void getStringWithInformationRegardingOneDirection() {
    assertEquals(
        "From here, you can go: north", room2.getStringWithInformationRegardingDirections());
  }

  @Test
  public void getStringWithInformationRegardingMoreThanTwoItems() {
    assertEquals(
        "This room contains item1, item2, item3\n", room.getStringWithInformationRegardingItems());
  }
}
