package stages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * The Deploy class automates the deployment of a project to a GitHub repository.
 * It reads the GitHub credentials from the config.properties file and uses Git commands
 * to commit and push the changes to the specified repository.
 */
public class Deploy {

  private static final String CONFIG_FILE = "config.properties";
  private static Properties properties = new Properties();

  /**
   * Constructor for the Deploy class.
   * Loads GitHub credentials from the config.properties file.
   */
  public Deploy() {
    try (InputStream input = Deploy.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      if (input == null) {
        System.out.println("Sorry, unable to find " + CONFIG_FILE);
        return;
      }
      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Deploys the project to the GitHub repository.
   * The method stages the files, commits the changes, and pushes them to the repository.
   *
   * @return true if the deployment is successful, false otherwise.
   */
  public boolean deploy() {
    String githubUsername = properties.getProperty("github.username");
    String githubToken = properties.getProperty("github.token");
    String repoUrl = properties.getProperty("github.repo_url");

    if (githubUsername == null || githubToken == null || repoUrl == null) {
      System.out.println("GitHub credentials or repository URL are missing.");
      return false;
    }

    try {
      // Set up the Git configuration with the credentials
      setGitCredentials(githubUsername, githubToken);

      // Add files to Git, commit changes, and push to the GitHub repository
      executeGitCommand("git add .");
      executeGitCommand("git commit -m 'Automated deployment from pipeline'");
      executeGitCommand("git push " + repoUrl);

      System.out.println("Deployment to GitHub successful.");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Sets up Git credentials for authentication using the GitHub username and token.
   *
   * @param username GitHub username.
   * @param token GitHub personal access token.
   * @throws IOException if an I/O error occurs.
   * @throws InterruptedException if the process is interrupted.
   */
  private void setGitCredentials(String username, String token) throws IOException, InterruptedException {
    // Store credentials in the git-credentials helper
    String credentialHelperCommand = "git config --global credential.helper store";
    executeGitCommand(credentialHelperCommand);

    // Use the token for GitHub authentication
    String authString = "https://" + username + ":" + token + "@github.com";
    executeGitCommand("git remote set-url origin " + authString);
  }

  /**
   * Executes the given Git command in the system's shell.
   *
   * @param command The Git command to execute (e.g., "git add .", "git commit -m 'message'").
   * @throws IOException if an I/O error occurs while executing the command.
   * @throws InterruptedException if the process is interrupted while waiting for the command to finish.
   */
  private void executeGitCommand(String command) throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();

    // Read the output from the command
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    }

    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new RuntimeException("Git command failed: " + command);
    }
  }
}
