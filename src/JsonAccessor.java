import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import java.net.MalformedURLException;

/**
 * . Class that contains the function getting JSON as string from the URL
 *
 * @author diproray
 */
public class JsonAccessor {

  // Needs updating for new JSON file.
  private static final String url = "https://diproray.github.io/samplemonster.json";

  /**
   * . Function that returns the stored URL
   *
   * @return the URL as a string
   */
  public static String getGivenUrl() {
    return url;
  }

  /**
   * . Function that returns a JSON string of the contents of the URL provided. No need to handle
   * exceptions here as only a private member variable is made used of here. Pre - Condition: the
   * member String URL is of correct format.
   *
   * @return a JSON string
   */
  public static String getJsonFromUrl(String url) {

    try {
      HttpRequest request = Unirest.get(url);
      HttpResponse<JsonNode> jsonResponse = request.asJson();

      String jsonResponseString = jsonResponse.getBody().toString();
      return jsonResponseString;

    } catch (Exception e) {
      return "Error occurred during fetching the JSON from the URL. Please try again with correct resources.";
    }
  }
}
