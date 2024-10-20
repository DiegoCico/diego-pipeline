import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class config {
    private Properties properties = new Properties();

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

    // Method to get a property value by its key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        config configLoader = new config();

        // Access configuration values
        System.out.println("APP NAME: " + configLoader.getProperty("app.name"));
        System.out.println("APP VERSION: " + configLoader.getProperty("app.version"));
    }
}
