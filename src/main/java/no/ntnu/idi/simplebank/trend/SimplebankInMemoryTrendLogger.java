package no.ntnu.idi.simplebank.trend;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/11/12
 * Time: 14:48
 */
public class SimplebankInMemoryTrendLogger implements SimplebankInMemoryTrendLoggerInterface {

    private static volatile SimplebankInMemoryTrendLogger singletonInstance;

    public static SimplebankInMemoryTrendLogger getInstance() {
        if (singletonInstance == null) {
            synchronized (SimplebankInMemoryTrendLogger.class) {
                if (singletonInstance == null) {
                    singletonInstance = new SimplebankInMemoryTrendLogger();
                }
            }
        }
        return singletonInstance;
    }

    public void log(SimplebankTrendEvent event) {
        SimplebankInMemoryTrendDataStore.getInstance().addEvent(event);
    }
}
