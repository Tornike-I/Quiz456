package quiz5;

public class DummyCommunicationManager {

    public static String getDefaultResponse(String userMessage) {
        switch (userMessage.toLowerCase()) {
            case "hello":
                return "Good day";
            case "what time is it?":
                return "9:00 AM";
            case "i should go!":
                return "Wait for me!";
            default:
                return "I don't understand that.";
        }
    }
}
