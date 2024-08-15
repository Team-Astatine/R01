package teamzesa.DataBase.entity.RObject;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public record User(
        UUID uuid,
        List<String> nameList,
        HashSet<String> connectionIPList,
        int joinCount,
        int level,
        boolean isGodMode,
        boolean isAnnouncing
) implements RObject {
}
