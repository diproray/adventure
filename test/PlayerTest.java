import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  Player player = new Player("Dipro", null, 100, 100, 100, 1);
  Player updatedPlayer = new Player("Dipro", null, 100, 100, 200, 1);

  @Test
  public void setHealth() {
    player.setHealth(200);
    assertEquals(updatedPlayer, player);
  }

  @Test
  public void equalsNull() {
    assertFalse(player.equals(null));
  }
}
