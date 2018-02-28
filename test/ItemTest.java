import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

  @Test
  public void getName() {
    assertEquals("coin", Sample.ITEM_ONE.getName());
  }

  @Test
  public void getDamage() {
    assertTrue(100 == Sample.ITEM_ONE.getDamage());
  }

  @Test
  public void equalsDifferentObjectWithSameValues() {
    assertTrue(Sample.ITEM_ONE.equals(new Item("coin", 100)));
  }

  @Test
  public void equalsNull() {
    assertFalse(Sample.ITEM_ONE.equals(null));
  }

  @Test
  public void equalsSameObject() {
    assertTrue(Sample.ITEM_ONE.equals(Sample.ITEM_ONE));
  }
}
