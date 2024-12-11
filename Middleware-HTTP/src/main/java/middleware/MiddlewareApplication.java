package middleware;

import org.springframework.boot.SpringApplication;
// import io.github.cdimascio.dotenv.Dotenv; // For mvn run
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

/**
 * The entry point for the Middleware application.
 *
 * <p>
 * This class contains the main method which serves as the starting point for
 * the Spring Boot application. It loads environment variables from a .env file
 * to configure database and JWT settings.
 * </p>
 */
@SpringBootApplication
public class MiddlewareApplication {

    /**
     * The main method that runs the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {

        // Load the .env file (For mvn run)
        // Dotenv dotenv = Dotenv.configure()
        // .load();
        // System.setProperty("DB_URL", dotenv.get("DB_URL"));
        // System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        // System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        // System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        // System.setProperty("ORIGIN", dotenv.get("ORIGIN"));
        printTrace();
        SpringApplication.run(MiddlewareApplication.class, args);
    }
    public static void printTrace(){
        try (ScanResult scanResult = // Assign scanResult in try-with-resources
                new ClassGraph() // Create a new ClassGraph instance
                        .verbose() // If you want to enable logging to stderr
                        .enableAllInfo() // Scan classes, methods, fields, annotations
                        .acceptPackages("middleware") // Scan com.xyz and subpackages
                        .scan()) { // Perform the scan and return a ScanResult
            // Use the ScanResult within the try block, e.g.
            ClassInfo widgetClassInfo = scanResult.getClassInfo("middleware.util.JwtUtil");
            ClassInfoList widgetList = scanResult.getAllClasses();
            System.out.println(widgetList.toString());
        }catch (Exception e) {
           e.printStackTrace();
        }
    }
}
