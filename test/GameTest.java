import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

  @Test
  public void Player() throws Exception {
    assertTrue((Sample.SAMPLE_GAME_READ_ONLY.equals(new Game("samplemonster.json"))));
  }

  @Test
  public void PlayerUrl() throws Exception {
    Game game = new Game();
    assertTrue(game.equals(Sample.SAMPLE_GAME_READ_ONLY));
  }

  @Test
  public void takeItemNull() {
    assertEquals("Your input item name is invalid\n", Sample.SAMPLE_GAME_TWO.takeItem(null));
  }

  @Test
  public void takeItemInvalid() {
    assertEquals("I can't take Macbook.\n", Sample.SAMPLE_GAME_FOUR.takeItem("Macbook"));
  }

  @Test
  public void takeItemValid() {
    assertEquals("", Sample.SAMPLE_GAME_FOUR.takeItem("coin"));
  }

  @Test
  public void takeItemValidWithMonstersPresent() {
    assertEquals(
        "There are still monsters here, I can't take that.\n", Sample.SAMPLE_GAME.takeItem("coin"));
  }

  @Test
  public void dropItemInvalid() {
    assertEquals("I can't drop pencil." + "\n", Sample.SAMPLE_GAME.dropItem("pencil"));
  }

  @Test
  public void dropItemNull() {
    assertEquals("Your input item name is invalid\n", Sample.SAMPLE_GAME_TWO.dropItem(null));
  }

  @Test
  public void dropItemValid() {
    assertEquals("", Sample.SAMPLE_GAME_THREE.dropItem("coin"));
  }

  @Test
  public void printItemsOfThePlayerNull() {
    assertEquals("You are carrying nothing\n", Sample.SAMPLE_GAME_THREE.printItemsOfThePlayer());
  }

  @Test
  public void printItemsOfThePlayer() {
    assertEquals("You are carrying coin\n", Sample.SAMPLE_GAME_FIVE.printItemsOfThePlayer());
  }

  @Test
  public void moveInADirectionInvalid() throws Exception {
    assertEquals("I can't go West.\n", Sample.SAMPLE_GAME_TWO.moveInADirection("West"));
  }

  @Test
  public void moveInADirectionValid() throws Exception {
    assertEquals("", Sample.SAMPLE_GAME_TWO.moveInADirection("East"));
  }

  @Test
  public void moveInADirectionValidWithMonstersPresent() throws Exception {
    assertEquals(
        "There are still monsters here, I can't move.\n",
        Sample.SAMPLE_GAME.moveInADirection("East"));
  }

  @Test
  public void getCurrentRoom() {
    assertEquals(Sample.ROOM_ONE, Sample.SAMPLE_GAME.getCurrentRoom());
  }

  @Test
  public void getGameLayout() {
    assertEquals(Sample.SAMPLE_LAYOUT, Sample.SAMPLE_GAME.getGameLayout());
  }

  @Test
  public void equalsNull() {
    assertFalse(Sample.SAMPLE_GAME.equals(null));
  }

  @Test
  public void equalsSameObject() {
    assertTrue(Sample.SAMPLE_GAME.equals(Sample.SAMPLE_GAME));
  }

  @Test
  public void getCurrentRoomInformation() {
    assertEquals(
        Sample.ROOM_ONE_READ_ONLY.getInformation(Sample.SAMPLE_LAYOUT_READ_ONLY),
        Sample.SAMPLE_GAME_READ_ONLY.getCurrentRoomInformation());
  }

  @Test
  public void getPlayerInfo() {
    assertEquals(
        Sample.SAMPLE_LAYOUT_READ_ONLY.getPlayerInfo(),
        Sample.SAMPLE_GAME_READ_ONLY.getPlayerInfo());
  }

  @Test
  public void printItemsOfThePlayerThreeItems() {
    assertEquals(
        "You are carrying coin, bazooka, pencil\n",
        Sample.SAMPLE_GAME_READ_ONLY_TWO.printItemsOfThePlayer());
  }

  @Test
  public void getCurrentDuelInformation() {
    assertEquals(
        "Name: Dipro Ray; Level: 1; Attack: 350.0; Defense: 450.0; Health: 1000.0\n"
            + "Name: Cookie Monster; Attack: 250.0; Defense: 150.0; Health: 750.0",
        Sample.SAMPLE_GAME_READ_ONLY_TWO.getCurrentDuelInformation(Sample.MONSTER_ONE_READ_ONLY));
  }

  @Test
  public void getCurrentDuelHealthBars() {
    assertEquals(
        "Player:  |#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|\n"
            + "Monster: |#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|",
        Sample.SAMPLE_GAME_READ_ONLY_TWO.getCurrentDuelHealthBars(Sample.MONSTER_ONE_READ_ONLY));
  }

  @Test
  public void attackMonsterWithItemInvalid() {
    assertEquals(
        "Item not found.",
        Sample.SAMPLE_GAME_SIX.attackMonsterWithItem(Sample.MONSTER_ONE_READ_ONLY, "me"));
  }

  @Test
  public void attackMonsterWithItemValid() {
    Sample.SAMPLE_GAME_SIX.attackMonsterWithItem(Sample.MONSTER_ONE_CHANGING, "coin");
    assertEquals(275.0, Sample.MONSTER_ONE_CHANGING.getHealth(), 0.0001);
  }

  @Test
  public void attackMonsterValidDead() {
    assertEquals(
        "Monster is dead. You win this duel.",
        Sample.SAMPLE_GAME_SIX.attackMonster(Sample.MONSTER_ONE_NEGATIVE_COPY_CHANGING));
  }

  @Test
  public void attackMonsterValid() {
    Sample.SAMPLE_GAME_SIX.attackMonster(Sample.MONSTER_ONE_COPY_CHANGING);
    assertEquals(375.0, Sample.MONSTER_ONE_COPY_CHANGING.getHealth(), 0.0001);
  }
}
