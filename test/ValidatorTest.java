import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

  @Test
  public void mapValidatorWrapperForValidFile() throws Exception {
    Game testGame = new Game("samplemonster.json");
    assertEquals("", Validator.mapValidatorWrapper(testGame.getGameLayout()));
  }

  @Test
  public void floorPlanValidatorForValidFile() throws Exception {
    Game testGame = new Game("samplemonster.json");
    assertEquals("", Validator.floorPlanValidator(testGame.getGameLayout()));
  }

}
