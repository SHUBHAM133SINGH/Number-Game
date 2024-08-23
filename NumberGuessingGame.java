import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        final int MIN_RANGE = 1;
        final int MAX_RANGE = 100;
        final int MAX_ATTEMPTS = 7;
        int totalScore = 0;
        
        System.out.println("Welcome to the Number Guessing Game!\n");

        boolean continuePlaying = true;
        
        while (continuePlaying) {
            int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
            System.out.printf("New Round - Number is between %d and %d\n", MIN_RANGE, MAX_RANGE);
            System.out.println("You have " + MAX_ATTEMPTS + " tries to guess the number.\n");
            
            int attemptCount = 0;
            boolean isCorrectGuess = false;
            
            while (attemptCount < MAX_ATTEMPTS) {
                System.out.print("Please enter your guess: ");
                
                // Handle invalid input
                int userGuess = getUserGuess(scanner);
                
                attemptCount++;
                
                if (userGuess == targetNumber) {
                    System.out.printf("Congratulations! You guessed the number in %d tries.\n", attemptCount);
                    totalScore += (MAX_ATTEMPTS - attemptCount + 1); // Rewarding more for fewer attempts
                    isCorrectGuess = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low. Try again.\n");
                } else {
                    System.out.println("Your guess is too high. Try again.\n");
                }
            }
            
            if (!isCorrectGuess) {
                System.out.println("You've used all your attempts. The number was " + targetNumber);
            }
            
            System.out.println("Your current score: " + totalScore + "\n");
            
            // Check if the user wants to play another round
            System.out.print("Would you like to play another round? (yes/no): ");
            continuePlaying = checkPlayAgain(scanner);
        }
        
        System.out.println("Thank you for playing! Your final score is: " + totalScore);
        scanner.close();
    }

    private static int getUserGuess(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static boolean checkPlayAgain(Scanner scanner) {
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }
}
