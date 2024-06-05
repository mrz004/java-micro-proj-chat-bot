package chatbot.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API_Handler {

  private String URL;
  private static final String MODEL = "gpt-3.5-turbo-instruct";
  private static final String METHOD = "POST";
  private static final int MAX_TOKENS = 500;
  private static final float TEMPERATURE = 0;
  private JSONParser parser;

  private void init() {
    this.parser = new JSONParser();
  }

  // dev: Constructors
  public API_Handler(String url) {
    this.URL = url;
    this.init();
  }

  public API_Handler() {
    // dev: Open API URL
    this.URL = "https://api.openai.com/v1/completions";
    this.init();
  }

  // *** Sample Request URL
  //   curl https://api.openai.com/v1/models \
  //   -H "Authorization: Bearer $OPENAI_API_KEY" \
  //   -H "OpenAI-Organization: org-JWV22xFAboEsy4RQscJ9UAgy" \
  //   -H "OpenAI-Project: $PROJECT_ID"

  public String sendRequest(String prompt) {
    HttpResponse<String> response = null;
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(this.URL))
      .header("Authorization", " Bearer " + System.getenv("OPENAI_API_KEY"))
      .header("Content-Type", "application/json")
      .method(
        METHOD,
        HttpRequest.BodyPublishers.ofString(
          "{" + //
          "\"model\": \"" +
          MODEL +
          "\"," + //
          "\"prompt\": \"" +
          prompt +
          "\"," + //
          "\"max_tokens\": " +
          MAX_TOKENS +
          "," + //
          "\"temperature\": " +
          TEMPERATURE + //
          "}"
        )
      )
      .build();

    try {
      response =
        HttpClient
          .newHttpClient()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      return e.toString();
    }

    return parser.parseMessage(response.body());
  }
}
