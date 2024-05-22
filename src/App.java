// import chatbot.API_Handler;
// import chatbot.JSONParser;
// import java.util.Scanner;

import chatbot.GUI;

public class App {

  public static void main(String[] args) {
    // API_Handler handler = new API_Handler();
    // Scanner sc = new Scanner(System.in);
    // JSONParser parser = new JSONParser();
    // String p = "";
    // String r = "";
    // // String r =
    // //   "{\"created\":1716264685,\"usage\":{\"completion_tokens\":10,\"prompt_tokens\":1,\"total_tokens\":11},\"model\":\"gpt-3.5-turbo-instruct\",\"id\":\"cmpl-9RB8PgEFNvPcaQvfNCebwjdUc7rLm\",\"choices\":[{\"finish_reason\":\"stop\",\"index\":0,\"text\":\"\\n" + //
    // //   "\\n" + //
    // //   "\\n" + //
    // //   "Hello! How are you doing today?\",\"logprobs\":null}],\"object\":\"text_completion\"}";

    // while (!p.equals("stop")) {
    //   System.out.print("Enter the prompt : ");
    //   p = sc.nextLine();
    //   r = handler.sendRequest(p);
    //   System.out.println(parser.parseMessage(r));
    // }

    new GUI();
  }
}
