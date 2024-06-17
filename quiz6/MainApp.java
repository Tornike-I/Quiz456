package quiz6;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        SpecialCommunicationManager manager = new SpecialCommunicationManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            manager.chatHistory += "User: " + userInput + "\n";

            String botResponse = manager.getChatbotResponse(userInput);
            System.out.println("Chatbot: " + botResponse);
            manager.chatHistory += "Chatbot: " + botResponse + "\n";
        }
    }
}
