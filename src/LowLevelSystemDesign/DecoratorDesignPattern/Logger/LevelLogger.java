package LowLevelSystemDesign.DecoratorDesignPattern.Logger;

public class LevelLogger extends AbstractLogger{

    private final Logger logger;
    private final String level;

    public LevelLogger(Logger logger, String level) {
        this.logger = logger;
        this.level = level;
    }


    @Override
    public void log(String message) {
        message = "[" + level + "] " + message;
        logger.log(message);
    }
}
