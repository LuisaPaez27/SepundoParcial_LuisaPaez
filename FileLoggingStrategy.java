import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLoggingStrategy implements ErrorLoggingStrategy {
    private static final String FILE_NAME = "error_log.txt";

    @Override
    public void logError(String errorMessage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
