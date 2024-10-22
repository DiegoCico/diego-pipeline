package stages;

import org.junit.platform.console.ConsoleLauncher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * The Test class handles running JUnit tests as specified in the config.properties file.
 * It dynamically loads test files, runs them, and tracks progress with a progress bar.
 */
public class Test {

  private static final String CONFIG_FILE = "config.properties";
  private static Properties properties = new Properties();
  private static List<String> testFiles;

  // ANSI escape codes for colored output
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RED = "\u001B[31m";

  /**
   * Loads the test file names from the config.properties file.
   */
  public static void loadConfig() {
    try (InputStream input = Test.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      if (input == null) {
        System.out.println("Sorry, unable to find " + CONFIG_FILE);
        return;
      }
      properties.load(input);

      String tests = properties.getProperty("tests");
      if (tests != null && !tests.isEmpty()) {
        testFiles = Arrays.asList(tests.split(","));
      } else {
        System.out.println("No tests found in config.properties");
        testFiles = null;
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Runs the tests specified in the config.properties file.
   * Displays a progress bar as the tests are executed.
   *
   * @return true if all tests pass, false if any test fails.
   */
  public static boolean runTests() {
    loadConfig();

    if (testFiles == null || testFiles.isEmpty()) {
      System.out.println("No tests found.");
      return true;  // No tests to run, assume passing
    }

    boolean allTestsPassed = true;
    int totalTests = testFiles.size();
    int completedTests = 0;

    // Loop through the test scripts and execute them using JUnit's ConsoleLauncher
    for (String testFile : testFiles) {
      System.out.println("Running test: " + testFile.trim());

      // Use JUnit ConsoleLauncher to run the test class dynamically
      String[] args = new String[]{"--select-class", "stages." + testFile.trim()};

      int exitCode = ConsoleLauncher.execute(System.out, System.err, args).getExitCode();
      if (exitCode != 0) {
        printInRed("Test failed: " + testFile.trim());
        allTestsPassed = false;
      } else {
        printInGreen("Test passed: " + testFile.trim());
      }

      // Update progress bar after each test
      completedTests++;
      updateProgressBar(completedTests, totalTests);
    }

    return allTestsPassed;
  }

  /**
   * Updates the progress bar based on the number of completed tests.
   *
   * @param completedTests The number of tests completed.
   * @param totalTests The total number of tests to run.
   */
  private static void updateProgressBar(int completedTests, int totalTests) {
    int progressPercentage = (completedTests * 100) / totalTests;
    final int progressBarLength = 50;  // Length of the progress bar

    StringBuilder progressBar = new StringBuilder("[");
    int filledLength = (progressPercentage * progressBarLength) / 100;

    // Fill the progress bar with '=' and ' ' for incomplete sections
    for (int i = 0; i < progressBarLength; i++) {
      if (i < filledLength) {
        progressBar.append("=");
      } else {
        progressBar.append(" ");
      }
    }

    progressBar.append("] ").append(progressPercentage).append("%");
    System.out.print("\r" + progressBar.toString());
    if (progressPercentage == 100) {
      System.out.println();  // Print a new line when progress is complete
    }
  }

  /**
   * Prints the provided message in green text.
   *
   * @param message The message to print in green.
   */
  private static void printInGreen(String message) {
    System.out.println(ANSI_GREEN + message + ANSI_RESET);
  }

  /**
   * Prints the provided message in red text.
   *
   * @param message The message to print in red.
   */
  private static void printInRed(String message) {
    System.out.println(ANSI_RED + message + ANSI_RESET);
  }
}
