import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogTestClass {
    private static Logger log = LogManager.getLogger(LogTestClass.class);
    public static void main(String[] args) {
        log.info("Я инфо лог");
        log.debug("Я дебаг лог");
    }
}
