package chatbot.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

  public String parseMessage(String jsoString) {
    JSONObject obj = new JSONObject(jsoString);
    JSONArray choices = obj.getJSONArray("choices");
    JSONObject msg = choices.getJSONObject(0);
    return msg.getString("text");
  }
}
