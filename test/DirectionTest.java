import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

  @Test
  public void getDirectionName() {
    assertEquals("East", Sample.DIRECTION_ONE.getDirectionName());
  }

  @Test
  public void getRoom() {
    assertEquals("Siebel1314", Sample.DIRECTION_ONE.getRoom());
  }

  @Test
  public void equalsDifferentObjectWithSameValues() {
    assertTrue(Sample.DIRECTION_ONE.equals(new Direction("East", "Siebel1314")));
  }

  @Test
  public void equalsNull() {
    assertFalse(Sample.DIRECTION_ONE.equals(null));
  }

  @Test
  public void equalsSameObject() {
    assertTrue(Sample.DIRECTION_ONE.equals(Sample.DIRECTION_ONE));
  }
}
