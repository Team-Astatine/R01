package teamzesa.DataBase.entity;

import java.util.UUID;

public record UserKillStatus(
        UUID uuid,
        double healthScale,
        int killCount
) implements RObject {}
