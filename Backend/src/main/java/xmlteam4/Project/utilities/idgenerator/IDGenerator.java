package xmlteam4.Project.utilities.idgenerator;

import com.devskiller.friendly_id.FriendlyId;

public class IDGenerator {
    public static String createID() {
        return FriendlyId.createFriendlyId();
    }
}
