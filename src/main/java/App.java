import stages.Build;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting the build pipeline...");
        checkBuild();
    }

    public static void checkBuild() {
        System.out.println("Checking build...");
        Build buildStage = new Build();
        boolean buildSuccess = buildStage.execute();
        if (buildSuccess) {
            System.out.println("Build succeeded! Moving to the next stage...");
        } else {
            System.out.println("Build failed. Stopping pipeline.");
            System.exit(0);
        }
    }
}
