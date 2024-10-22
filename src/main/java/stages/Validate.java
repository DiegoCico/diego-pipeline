package stages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The Validate class handles the validation of a program by running it and asking the user
 * if it worked as intended. If the program runs successfully and the user confirms, the validation is considered successful.
 */
public class Validate {

  /**
   * Runs the program and prompts the user for validation input after it finishes running.
   *
   * @return true if the program ran successfully and the user confirms it worked as intended, false otherwise.
   */
  public static boolean validateProgram() {
    System.out.println("Starting program validation...");

    // Run the program (assuming it's a Java program for this example)
    boolean programRanSuccessfully = runProgram();

    if (programRanSuccessfully) {
      // Program ran, now ask the user for confirmation
      return askUserIfProgramWorked();
    } else {
      System.out.println("Program did not run successfully. Validation failed.");
      return false;
    }
  }

  /**
   * Runs the Java program using the ProcessBuilder.
   * The program's output is inherited by the current process, so it's shown in the console.
   *
   * @return true if the program runs and exits successfully, false otherwise.
   */
  private static boolean runProgram() {
    try {
      // Command to run the Java program (replace 'YourMainClass' with the actual class)
      ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "target/classes", "stages.YourMainClass");
      processBuilder.inheritIO();  // This will redirect program output to the console

      Process process = processBuilder.start();
      int exitCode = process.waitFor();

      if (exitCode == 0) {
        System.out.println("Program finished successfully.");
        return true;
      } else {
        System.out.println("Program exited with errors.");
        return false;
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Asks the user if the program worked as intended after it has been run.
   *
   * @return true if the user confirms that the program worked as intended, false otherwise.
   */
  private static boolean askUserIfProgramWorked() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Did the program work as intended? (yes/no): ");
    String userInput = scanner.nextLine().trim().toLowerCase();

    if (userInput.equals("yes")) {
      System.out.println("Program validated successfully!");
      return true;  // Move to the next stage
    } else {
      System.out.println("Validation failed. Program did not work as expected.");
      return false;  // Validation failed
    }
  }
}
