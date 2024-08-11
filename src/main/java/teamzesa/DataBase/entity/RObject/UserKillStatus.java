package teamzesa.DataBase.entity.RObject;

import java.util.UUID;

public record UserKillStatus(
        UUID uuid,
        double healthScale,
        int killCount
) implements RObject {
}
