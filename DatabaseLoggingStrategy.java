public class DatabaseLoggingStrategy implements ErrorLoggingStrategy {
    @Override
    public void logError(String errorMessage) {
        throw new UnsupportedOperationException("Database logging strategy not implemented: " + errorMessage);
    }
}
