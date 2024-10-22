import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class loads configuration properties from the "config.properties" file.
 * It provides a method to retrieve property values by key.
 */
public class config {

    private Properties properties = new Properties();

    /**
     * Constructor for config class.
     * Loads properties from the "config.properties" file.
     */
    public config() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves the value of a property by its key.
     * @param key The key of the property.
     * @return The value of the property, or null if the key does not exist.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Main method for testing the loading of configuration properties.
     * Prints out the "app.name" and "app.version" from the properties file.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        config configLoader = new config();

        // Access configuration values
        System.out.println("APP NAME: " + configLoader.getProperty("app.name"));
        System.out.println("APP VERSION: " + configLoader.getProperty("app.version"));
    }
}
