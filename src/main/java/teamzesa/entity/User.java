package teamzesa.entity;

import java.util.*;

public record User (
        UUID uuid,
        String name,
        HashSet<String> connectionIPList,
        int joinCount,
        int level,
        double healthScale,
        int killStatus,
        boolean godMode
        ) {}
