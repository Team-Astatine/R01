package teamzesa.Data.User.UserKillStatus;

import teamzesa.Data.Interface.User.RObject;

import java.util.UUID;

public record UserKillStatus(
        UUID uuid,
        double healthScale,
        int killCount
) implements RObject {
}
