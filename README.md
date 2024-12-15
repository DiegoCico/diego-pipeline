# Build and Deployment Pipeline

This project implements a complete build, test, validation, and deployment pipeline for a Java project. It includes stages for building the project, running tests, validating the program's behavior through user input, and automatically deploying it to a GitHub repository.

## Project Overview

This pipeline consists of the following stages:
1. **Build Stage**: Compiles the source files and checks for errors.
2. **Test Stage**: Runs JUnit tests specified in the configuration file and shows progress with a progress bar.
3. **Validation Stage**: Executes the program and prompts the user to confirm if it worked as intended.
4. **Deployment Stage**: Deploys the project to a GitHub repository by committing and pushing changes automatically.

### Configuration
The pipeline configuration is managed through a `config.properties` file. It contains the necessary details for the scripts to be built, the tests to be run, and the GitHub credentials for deployment.

### Tools Used
- **JUnit**: For running unit tests.
- **Git**: For version control and deployment to GitHub.
- **Java ProcessBuilder**: For running external programs and scripts.
  
## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK)
- Maven (if applicable)
- Git
- JUnit for running tests

# List of scripts to compile and tests to run
scripts=YourMainClass.java
tests=TestClass1,TestClass2

markdown

1. **Build Stage**: Compile the Java source files.
2. **Test Stage**: Run unit tests and show a progress bar for test completion.
3. **Validation Stage**: Execute the program and ask for user confirmation.
4. **Deploy Stage**: Automatically push changes to GitHub.

To run the pipeline, execute the main entry point:

mvn clean install  # For Maven projects
java -cp target/classes stages.App


### Build Stage
The `Build` class handles compiling source files and checking for errors in various file types (e.g., Java, Python, JavaScript). It simulates the build process and provides colored terminal output to indicate success or failure.

### Test Stage
The `Test` class runs JUnit tests specified in `config.properties`. It dynamically executes test classes using the `ConsoleLauncher` and provides a visual progress bar to track the completion of all tests. If any test fails, the pipeline will stop, and errors will be printed in red.
### Validation Stage
The `Validate` class runs the program and asks the user to confirm if it worked as intended. After the program finishes, the user will be prompted to input `yes` or `no` based on the program's behavior.

### Deployment Stage
The `Deploy` class handles pushing the project to a GitHub repository. It commits all changes and pushes to the specified repository using the credentials provided in the `config.properties` file. 

To deploy the project to GitHub:
1. Ensure your GitHub username and personal access token are correctly configured in `config.properties`.
2. Run the pipeline, and the final step will automatically push changes to your GitHub repository.

java
// Example for manually triggering the deployment stage
Deploy deployStage = new Deploy();
boolean deploySuccess = deployStage.deploy();
