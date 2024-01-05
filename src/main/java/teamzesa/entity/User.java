package teamzesa.entity;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.util.*;

public record User (
        UUID uuid,
        String name,
        HashSet<String> ipList,
        int joinCount,
        int level,
        double healthScale,
        int killStatus,
        boolean godMode
        ) {}
