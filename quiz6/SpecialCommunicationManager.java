// File: SpecialCommunicationManager.java
package quiz6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SpecialCommunicationManager {
    //replace the urls and api keys below with real ones, the function will use special service if the user message contains help
    private static final String COMMON_SERVICE_URL = "https://placeholder-common-chatbot-url.com/api/chat";
    private static final String SPECIAL_SERVICE_URL = "https://placeholder-special-chatbot-url.com/api/chat";
    private static final String COMMON_API_KEY = "common_api_key_here";
    private static final String SPECIAL_API_KEY = "special_api_key_here";

    String chatHistory = "";

    public String getChatbotResponse(String userMessage) {
        String endpointUrl = determineEndpointUrl(userMessage);
        String apiKey = determineApiKey(userMessage);

        String requestBody = "{"
                + "\"api_key\": \"" + escapeJson(apiKey) + "\","
                + "\"message\": \"" + escapeJson(userMessage) + "\","
                + "\"history\": \"" + escapeJson(chatHistory) + "\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpointUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseChatbotResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to get response from chatbot service. Enter the url, api key and the parsing function.";
        }
    }

    private String determineEndpointUrl(String userMessage) {
        if (userMessage.toLowerCase().contains("help") || chatHistory.toLowerCase().contains("help")) {
            return SPECIAL_SERVICE_URL;
        } else {
            return COMMON_SERVICE_URL;
        }
    }

    private String determineApiKey(String userMessage) {
        if (userMessage.toLowerCase().contains("help") || chatHistory.toLowerCase().contains("help")) {
            return SPECIAL_API_KEY;
        } else {
            return COMMON_API_KEY;
        }
    }

    private String parseChatbotResponse(String responseBody) {
        // Placeholder method for manual parsing of the response
        // Change this function in accordance with a chatbot being used.
        return responseBody;
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
    }
}
