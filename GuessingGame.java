import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int roundsWon = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            int maxAttempts = 10;
            int attemptsLeft = maxAttempts;
            int score = 0;

            int randomNumber = random.nextInt(100) + 1;
            System.out.println("Welcome to the Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");

            while (attemptsLeft > 0) {
                System.out.println("You have " + attemptsLeft + " attempts remaining.");
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low.");
                } else if (userGuess > randomNumber) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score = maxAttempts - attemptsLeft;
                    roundsWon++;
                    break;
                }

                if (attemptsLeft == 0) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was " + randomNumber + ".");
                }
            }

            System.out.println("Your score for this round: " + score);
            System.out.println("Total rounds won: " + roundsWon);

            System.out.print("Would you like to play another round? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no")) {
                playAgain = false;
                System.out.println("Thank you for playing!");
            }
        }

        scanner.close();
    }
}
