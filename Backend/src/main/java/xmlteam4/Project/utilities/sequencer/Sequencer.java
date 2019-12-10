package xmlteam4.Project.utilities.sequencer;

import java.util.concurrent.atomic.AtomicLong;

public class Sequencer {
    private static AtomicLong idCounter = new AtomicLong();

    public static String createID() {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
