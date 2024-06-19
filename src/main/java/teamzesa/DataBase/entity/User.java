package teamzesa.DataBase.entity;

import java.util.*;

public record User (
        UUID uuid,
        List<String> nameList,
        HashSet<String> connectionIPList,
        int joinCount,
        int level,
        boolean isGodMode,
        boolean isAnnouncing
) implements RObject {}
