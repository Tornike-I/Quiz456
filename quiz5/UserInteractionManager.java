package quiz5;

import java.util.Scanner;

public class UserInteractionManager {

    private static final String API_KEY = "enter your api key"; // Placeholder for API key, once you enter an API key,
    //the chatbot will use the API instead of default responses
    private static final String CHATBOT_URL = "https://your-chatbot-service-url.com/api/chat";// also enter the url for the api
    private static String chatHistory = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommunicationManager client = new CommunicationManager();

        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();
            chatHistory += "User: " + userMessage + "\n";

            String response;
            if (API_KEY.equals("enter your api key")) {
                response = DummyCommunicationManager.getDefaultResponse(userMessage);
            } else {
                response = client.getChatbotResponse(userMessage);
            }

            System.out.println("Chatbot: " + response);
            chatHistory += "Chatbot: " + response + "\n";
        }
    }
}
