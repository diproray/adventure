import org.junit.Test;

import static org.junit.Assert.*;

/** . Test class for JsonAccessor.java */
public class JsonAccessorTest {

  /** . Tests that getJsonFromUrl() works properly. */
  @Test
  public void getJsonFromUrl() {
    assertEquals(
        Sample.JSON_STRING_FROM_URL, JsonAccessor.getJsonFromUrl(JsonAccessor.getGivenUrl()));
  }

  @Test
  public void getJsonFromUrlInvalid() {
    assertEquals(
        "Error occurred during fetching the JSON from the URL. Please try again with correct resources.",
        JsonAccessor.getJsonFromUrl(null));
  }
}
