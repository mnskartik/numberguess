import javax.swing.JOptionPane;
import java.util.Random;

class NumberGuessingGame {

    public static void main(String[] args) {
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 5;
        int rounds = 0;
        int totalAttempts = 0;

        JOptionPane.showMessageDialog(null, "Welcome to the Number Guessing Game!");
        String message = String.format("Guess the number between %d and %d.\n", minRange, maxRange);
        JOptionPane.showMessageDialog(null, message);

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (!guessedCorrectly && attempts < attemptsLimit) {
                String guessString = JOptionPane.showInputDialog(null,
                        "Enter your guess (attempt " + (attempts + 1) + "): ");

                if (guessString == null) {
                    // User closed the dialog, treat it as exiting the game
                    playAgain = false;
                    break;
                }

                try {
                    int guess = Integer.parseInt(guessString);
                    if (guess < minRange || guess > maxRange) {
                        JOptionPane.showMessageDialog(null, "Your guess is out of the valid range.");
                        continue;
                    }

                    attempts++;
                    totalAttempts++;

                    if (guess == targetNumber) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number.");
                        guessedCorrectly = true;
                    } else if (guess < targetNumber) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Sorry, you've used all your attempts. The correct number was: " + targetNumber);
            }

            rounds++;
            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again? (yes/no): ",
                    "Play Again?", JOptionPane.YES_NO_OPTION);
            playAgain = response == JOptionPane.YES_OPTION;
        }

        JOptionPane.showMessageDialog(null, "\nGame Over!");
        String stats = String.format("Total rounds played: %d\nTotal attempts made: %d\n", rounds, totalAttempts);
        JOptionPane.showMessageDialog(null, stats);
    }
}
