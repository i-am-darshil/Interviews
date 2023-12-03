package LowLevelSystemDesign.DecoratorDesignPattern.Logger;

import java.time.Instant;

public class TimestampLogger extends AbstractLogger{

    Logger logger;
    public TimestampLogger (Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        message = Instant.now().toString() + " " + message;
        logger.log(message);
    }
}
