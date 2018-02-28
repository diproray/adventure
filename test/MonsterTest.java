import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

  Monster monster = new Monster("Zombie 2.0", 100, 100, 100);
  Monster updatedMonster = new Monster("Zombie 2.0", 100, 100, 200);

  @Test
  public void setHealth() {
    monster.setHealth(200);
    assertEquals(updatedMonster, monster);
  }

  @Test
  public void equalsNull() {
    assertFalse(monster.equals(null));
  }
}
