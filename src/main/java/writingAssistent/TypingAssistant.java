package writingAssistent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TypingAssistant {

    private static final Random RANDOM = new Random();

    static void main(String[] args) {

        try {
            String textToType;
            try (var is = TypingAssistant.class.getResourceAsStream("/writingAssistent/content.txt")) {
                if (is == null) {
                    throw new IOException();
                }
                textToType = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }

            Robot robot = new Robot();

            // Allow time to focus the target input field.
            System.out.println(
                    "Starting in 10 seconds. Focus your target input field."
            );

            Thread.sleep(10_000);

            // Establish the initial typing rhythm.
            double speedFactor = 0.85 + RANDOM.nextDouble() * 0.30;

            for (int i = 0; i < textToType.length(); i++) {

                char current = textToType.charAt(i);

                typeChar(robot, current);

                char next = i + 1 < textToType.length()
                        ? textToType.charAt(i + 1)
                        : '\0';

                long delay = calculateDelay(
                        current,
                        next,
                        speedFactor
                );

                Thread.sleep(delay);

                // Occasionally change the rhythm slightly.
                if (RANDOM.nextDouble() < 0.03) {
                    speedFactor = 0.85 + RANDOM.nextDouble() * 0.30;
                }
            }

            System.out.println("Typing complete!");

        } catch (AWTException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calculateDelay(
            char current,
            char next,
            double speedFactor
    ) {

        // Base delay for ordinary characters.
        long delay = randomBetween(45, 100);

        // Consecutive letters generally form a typing burst.
        if (Character.isLetter(current)
                && Character.isLetter(next)) {

            delay = randomBetween(35, 85);
        }

        // Spaces create small rhythm changes.
        if (current == ' ') {
            delay += randomBetween(20, 80);
        }

        // Sentence punctuation creates a longer pause.
        if (current == '.' || current == '!' || current == '?') {
            delay += randomBetween(180, 450);
        }

        // Commas and semicolons usually introduce shorter pauses.
        if (current == ',' || current == ';' || current == ':') {
            delay += randomBetween(100, 250);
        }

        // Paragraph boundaries introduce larger pauses.
        if (current == '\n') {
            delay += randomBetween(250, 600);
        }

        // Occasionally hesitate briefly.
        if (RANDOM.nextDouble() < 0.025) {
            delay += randomBetween(150, 350);
        }

        // Apply a small variation to the overall typing rhythm.
        return Math.max(20, Math.round(delay * speedFactor));
    }

    private static long randomBetween(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    private static void typeChar(Robot robot, char c) {

        if (c == '\n') {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return;
        }

        if (c == '\t') {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            return;
        }

        boolean shift = Character.isUpperCase(c)
                || "!@#$%^&*()_+{}|:\"<>?".indexOf(c) >= 0;

        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

        if (keyCode == KeyEvent.VK_UNDEFINED) {
            System.err.println(
                    "Unsupported character: " + c
            );
            return;
        }

        try {
            if (shift) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

        } finally {
            if (shift) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
}