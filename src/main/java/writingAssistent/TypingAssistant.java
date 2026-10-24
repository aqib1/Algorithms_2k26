import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TypingAssistant {

    void main(String[] args) {
        // The content you want the application to type
        String textToType = "Hello! This is being typed by a Java application at human speed.";

        try {
            System.out.println("Starting in 5 seconds... CLICK inside your target input field NOW!");
            Thread.sleep(10000); // 5-second countdown to position your cursor

            Robot robot = new Robot();
            Random random = new Random();

            for (char c : textToType.toCharArray()) {
                typeChar(robot, c);

                // HUMAN SPEED: Generate a random delay between 60ms and 180ms per character
                int delay = 60 + random.nextInt(120);

                // Add an extra pause for punctuation to make it look even more natural
                if (c == '.' || c == ',' || c == '!' || c == '?') {
                    delay += 300;
                }

                Thread.sleep(delay);
            }

            System.out.println("Typing complete!");

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Helper method to simulate press and release of keys
    private static void typeChar(Robot robot, char c) {
        boolean isUpperCase = Character.isUpperCase(c) || getSpecialShiftChars(c);

        if (isUpperCase) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }

        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

        if (keyCode != KeyEvent.VK_UNDEFINED) {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

        if (isUpperCase) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }

    // Quick check for characters that require SHIFT (e.g., !, ?, @)
    private static boolean getSpecialShiftChars(char c) {
        String shiftChars = "!@#$%^&*()_+{}|:\"<>?";
        return shiftChars.indexOf(c) != -1;
    }
}
