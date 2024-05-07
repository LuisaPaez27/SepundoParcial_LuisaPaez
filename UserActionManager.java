public class UserActionManager {
    private ErrorLoggingStrategy errorLoggingStrategy;
    private int consecutiveCount = 0;
    private String lastAction = "";

    public UserActionManager(ErrorLoggingStrategy errorLoggingStrategy) {
        this.errorLoggingStrategy = errorLoggingStrategy;
    }

    public void performAction(String action) {
        if (action.equals(lastAction)) {
            consecutiveCount++;
            if (consecutiveCount > 2) {
                errorLoggingStrategy.logError("Consecutive errors for action: " + action);
            }
        } else {
            consecutiveCount = 1;
        }
        lastAction = action;
        // Resto de la lógica para realizar la acción del usuario
    }

    public void setErrorLoggingStrategy(ErrorLoggingStrategy errorLoggingStrategy) {
        this.errorLoggingStrategy = errorLoggingStrategy;
    }
}
