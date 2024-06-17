// File: ChatbotClient.java
package quiz5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CommunicationManager {

    private static final String API_KEY = "your_api_key_here"; // Replace with your actual API key
    private static final String CHATBOT_URL = "https://your-chatbot-service-url.com/api/chat";
    private String chatHistory = "";

    public String getChatbotResponse(String userMessage) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            String requestBody = "{"
                    + "\"api_key\": \"" + escapeJson(API_KEY) + "\","
                    + "\"message\": \"" + escapeJson(userMessage) + "\","
                    + "\"history\": \"" + escapeJson(chatHistory) + "\""
                    + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(CHATBOT_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return parseChatbotResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to get response from chatbot service.";
        }
    }

    private String parseChatbotResponse(String responseBody) {
        return responseBody; // Placeholder method for actual parsing
    }

    private String escapeJson(String value) {
        // Function to manually escape characters in JSON strings
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
        // Add more replacements if necessary
    }
}
