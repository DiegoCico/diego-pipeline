package stages;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Build {

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    private List<String> sourceFiles;
    private static final String CONFIG_FILE = "config.properties";
    private Properties properties = new Properties();

    // Constructor to load the script names from config.properties
    public Build() {
        loadConfig();
        String scripts = properties.getProperty("scripts");
        if (scripts != null && !scripts.isEmpty()) {
            // Split the script names and trim any whitespace
            sourceFiles = Arrays.asList(scripts.split(","));
        } else {
            System.out.println("No scripts found in config.properties");
        }
    }

    // Simulate the entire build process with progress bar and colored text
    public boolean execute() {
        System.out.println("Executing build stage...");

        // Step 1: Simulate Compilation/Execution
        if (!simulateCompilation()) {
            printInRed("Compilation/Execution failed.");
            return false;
        }
        updateProgressBar(33);

        // Step 2: Simulate Testing
        if (!simulateTesting()) {
            printInRed("Tests failed.");
            return false;
        }
        updateProgressBar(66);

        // Step 3: Simulate Packaging
        if (!simulatePackaging()) {
            printInRed("Packaging failed.");
            return false;
        }
        updateProgressBar(100);

        printInGreen("Build successful.");
        return true;
    }

    // Simulate compilation/execution of source files with error checking
    private boolean simulateCompilation() {
        System.out.println("Compiling/Running source files...");

        if (sourceFiles == null || sourceFiles.isEmpty()) {
            System.out.println("No source files to compile.");
            return false;
        }

        for (String file : sourceFiles) {
            File sourceFile = new File("src/main/java/scripts/" + file.trim());
            if (!sourceFile.exists()) {
                printInRed("Source file not found: " + file);
                return false;
            }

            System.out.println("Processing " + sourceFile.getPath() + "...");

            // Handle different file formats based on file extension
            if (file.endsWith(".java")) {
                if (!simulateJavaCompilation(file)) return false;
            } else if (file.endsWith(".py")) {
                if (!simulatePythonExecution(file)) return false;
            } else if (file.endsWith(".js")) {
                if (!simulateJavaScriptExecution(file)) return false;
            } else if (file.endsWith(".ts")) {
                if (!simulateTypeScriptTranspile(file)) return false;
            } else if (file.endsWith(".c")) {
                if (!simulateCCompilation(file)) return false;
            } else if (file.endsWith(".cpp")) {
                if (!simulateCppCompilation(file)) return false;
            } else if (file.endsWith(".cs")) {
                if (!simulateCSharpCompilation(file)) return false;
            } else if (file.endsWith(".rb")) {
                if (!simulateRubyExecution(file)) return false;
            } else if (file.endsWith(".php")) {
                if (!simulatePhpExecution(file)) return false;
            } else if (file.endsWith(".swift")) {
                if (!simulateSwiftCompilation(file)) return false;
            } else if (file.endsWith(".go")) {
                if (!simulateGoCompilation(file)) return false;
            } else if (file.endsWith(".kt")) {
                if (!simulateKotlinCompilation(file)) return false;
            } else if (file.endsWith(".rs")) {
                if (!simulateRustCompilation(file)) return false;
            } else if (file.endsWith(".sh")) {
                if (!simulateBashExecution(file)) return false;
            } else if (file.endsWith(".r")) {
                if (!simulateRExecution(file)) return false;
            } else if (file.endsWith(".pl")) {
                if (!simulatePerlExecution(file)) return false;
            } else if (file.endsWith(".scala")) {
                if (!simulateScalaCompilation(file)) return false;
            } else if (file.endsWith(".dart")) {
                if (!simulateDartCompilation(file)) return false;
            } else if (file.endsWith(".hs")) {
                if (!simulateHaskellCompilation(file)) return false;
            } else if (file.endsWith(".ex")) {
                if (!simulateElixirExecution(file)) return false;
            } else {
                printInRed("Unsupported file type: " + file);
                return false;
            }
        }

        printInGreen("Compilation/Execution successful.");
        return true;
    }

    // Simulate various language compilation or execution methods
    private boolean simulateJavaCompilation(String file) {
        System.out.println("Compiling Java file: " + file);
        return !file.contains("Error");
    }

    private boolean simulatePythonExecution(String file) {
        System.out.println("Running Python file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateJavaScriptExecution(String file) {
        System.out.println("Running JavaScript file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateTypeScriptTranspile(String file) {
        System.out.println("Transpiling TypeScript file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateCCompilation(String file) {
        System.out.println("Compiling C file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateCppCompilation(String file) {
        System.out.println("Compiling C++ file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateCSharpCompilation(String file) {
        System.out.println("Compiling C# file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateRubyExecution(String file) {
        System.out.println("Running Ruby file: " + file);
        return !file.contains("Error");
    }

    private boolean simulatePhpExecution(String file) {
        System.out.println("Running PHP file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateSwiftCompilation(String file) {
        System.out.println("Compiling Swift file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateGoCompilation(String file) {
        System.out.println("Compiling Go file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateKotlinCompilation(String file) {
        System.out.println("Compiling Kotlin file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateRustCompilation(String file) {
        System.out.println("Compiling Rust file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateBashExecution(String file) {
        System.out.println("Running Bash script: " + file);
        return !file.contains("Error");
    }

    private boolean simulateRExecution(String file) {
        System.out.println("Running R script: " + file);
        return !file.contains("Error");
    }

    private boolean simulatePerlExecution(String file) {
        System.out.println("Running Perl script: " + file);
        return !file.contains("Error");
    }

    private boolean simulateScalaCompilation(String file) {
        System.out.println("Compiling Scala file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateDartCompilation(String file) {
        System.out.println("Compiling Dart file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateHaskellCompilation(String file) {
        System.out.println("Compiling Haskell file: " + file);
        return !file.contains("Error");
    }

    private boolean simulateElixirExecution(String file) {
        System.out.println("Running Elixir file: " + file);
        return !file.contains("Error");
    }

    // Simulate running tests with error checking
    private boolean simulateTesting() {
        System.out.println("Running tests...");

        for (String file : sourceFiles) {
            if (file.contains("Test")) {
                printInRed("Test failed in: " + file);
                return false;
            }
        }

        printInGreen("All tests passed.");
        return true;
    }

    // Simulate packaging the project into a JAR file with error checking
    private boolean simulatePackaging() {
        System.out.println("Packaging the project into a JAR file...");

        if (properties.getProperty("app.version") == null) {
            printInRed("Packaging failed due to missing version in config.properties");
            return false;
        }

        printInGreen("Packaging successful.");
        System.out.println("JAR file created: my-maven-project-1.0.jar");
        return true;
    }

    // Load the properties from config.properties
    private void loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Helper method to print in green and bold
    private void printInGreen(String message) {
        System.out.println(ANSI_BOLD + ANSI_GREEN + message + ANSI_RESET);
    }

    // Helper method to print in red and bold
    private void printInRed(String message) {
        System.out.println(ANSI_BOLD + ANSI_RED + message + ANSI_RESET);
    }

    // Simulate a status bar based on the percentage of progress
    private void updateProgressBar(int progress) {
        final int totalBars = 50; // Number of bars in the progress bar
        int filledBars = (int) ((progress / 100.0) * totalBars);
        StringBuilder progressBar = new StringBuilder("[");

        for (int i = 0; i < totalBars; i++) {
            if (i < filledBars) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }

        progressBar.append("] ").append(progress).append("%");
        System.out.print("\r" + progressBar.toString());
    }
}
