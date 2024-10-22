import stages.Build;
import stages.Test;
import stages.Deploy;

/**
 * The App class manages the build pipeline, executing build, test, and deploy stages.
 */
public class App {

    /**
     * The main entry point for the build pipeline.
     * It executes the build, test, and deploy stages sequentially.
     * @param args Command-line arguments (not used).
     * @throws Exception If an error occurs during the pipeline execution.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting the build pipeline...");

        boolean buildSuccess = checkBuild();  // Call the build check method
        if (buildSuccess) {
            boolean testsPassed = checkTest();  // Call the test check method
            if (testsPassed) {
                boolean deploySuccess = checkDeploy();  // Call the deploy check method
                if (deploySuccess) {
                    System.out.println("All stages completed successfully. The application has been deployed.");
                } else {
                    System.out.println("Deployment failed. Stopping pipeline.");
                    System.exit(1);  // Stop the pipeline due to deployment failure
                }
            } else {
                System.out.println("Tests failed. Stopping pipeline.");
                System.exit(1);  // Stop the pipeline due to failed tests
            }
        } else {
            System.out.println("Build failed. Stopping pipeline.");
            System.exit(0);  // Stop the pipeline due to build failure
        }
    }

    /**
     * Checks if the build stage passes.
     * @return true if the build succeeds, false otherwise.
     */
    public static boolean checkBuild() {
        System.out.println("Checking build...");
        Build buildStage = new Build();
        boolean buildSuccess = buildStage.execute();

        if (buildSuccess) {
            System.out.println("Build succeeded! Proceeding to the test stage...");
            return true;  // Build passed
        } else {
            System.out.println("Build failed. Stopping pipeline.");
            return false;  // Build failed
        }
    }

    /**
     * Checks if the test stage passes.
     * @return true if all tests pass, false otherwise.
     */
    public static boolean checkTest() {
        System.out.println("Running tests...");
        boolean testsPassed = Test.runTests();

        if (testsPassed) {
            System.out.println("All tests passed successfully.");
            return true;  // All tests passed
        } else {
            System.out.println("Some tests failed.");
            return false;  // Tests failed
        }
    }

    /**
     * Deploys the application to GitHub.
     * @return true if the deployment succeeds, false otherwise.
     */
    public static boolean checkDeploy() {
        System.out.println("Deploying the application to GitHub...");
        Deploy deployStage = new Deploy();
        boolean deploySuccess = deployStage.deploy();

        if (deploySuccess) {
            System.out.println("Deployment succeeded!");
            return true;  // Deployment passed
        } else {
            System.out.println("Deployment failed. Stopping pipeline.");
            return false;  // Deployment failed
        }
    }
}
