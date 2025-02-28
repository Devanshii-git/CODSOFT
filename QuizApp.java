import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static int totalQuestions = 0;
    private static int correctAnswers = 0;
    private static final int timeLimitInSeconds = 10;  // Time limit for each question in seconds

    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?", new String[] {"A. Paris", "B. London", "C. Berlin", "D. Madrid"}, "A"),
            new Question("Which planet is known as the Red Planet?", new String[] {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"}, "B"),
            new Question("Who wrote 'Harry Potter'?", new String[] {"A. J.R.R. Tolkien", "B. J.K. Rowling", "C. George R.R. Martin", "D. Suzanne Collins"}, "B"),
            new Question("What is 5 + 3?", new String[] {"A. 6", "B. 7", "C. 8", "D. 9"}, "C"),
            new Question("What is the square root of 16?", new String[] {"A. 3", "B. 4", "C. 5", "D. 6"}, "B")
        };

        for (Question q : questions) {
            totalQuestions++;
            askQuestion(q);
        }

        displayResult();
    }

    public static void askQuestion(Question q) {
        System.out.println("\nQuestion: " + q.questionText);
        for (String option : q.options) {
            System.out.println(option);
        }

        String userAnswer = getUserAnswer(q);
        checkAnswer(userAnswer, q.correctAnswer);
    }

    public static String getUserAnswer(Question q) {
        final String[] userAnswer = new String[1];  // Using an array to modify value inside TimerTask

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (userAnswer[0] == null) {
                    System.out.println("\nTime's up! Moving to next question...");
                    userAnswer[0] = ""; // Automatically marks the question as unanswered
                }
            }
        };

        timer.schedule(task, timeLimitInSeconds * 1000); // Set the timer for the question

        System.out.print("\nYour answer (A/B/C/D): ");
        userAnswer[0] = scanner.nextLine().toUpperCase();

        timer.cancel(); // Cancel the timer after answer submission or time-out

        return userAnswer[0];
    }

    public static void checkAnswer(String userAnswer, String correctAnswer) {
        if (userAnswer.equals(correctAnswer)) {
            System.out.println("Correct!");
            score++;
            correctAnswers++;
        } else if (userAnswer.equals("")) {
            System.out.println("No answer submitted. Correct answer was: " + correctAnswer);
        } else {
            System.out.println("Incorrect. Correct answer was: " + correctAnswer);
        }
    }

    public static void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score: " + score + " out of " + totalQuestions);
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + (totalQuestions - correctAnswers));
    }
}
