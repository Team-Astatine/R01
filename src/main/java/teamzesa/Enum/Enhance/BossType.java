package teamzesa.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BossType {
    DRAGON(EntityType.ENDER_DRAGON, 2, "엔더 드래곤"),
    WITHER(EntityType.WITHER, 6,"위더"),
    WARDEN(EntityType.WARDEN, 4,"워든");

    private final EntityType entityType;
    private final double rewardHealth;
    private final String bossName;
    private final static EnumMap<EntityType, BossType> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                            BossType::getEntityType,
                            Function.identity(),
                            (k1, k2) -> k1,
                            () -> new EnumMap<>(EntityType.class)
                    )
            );

    BossType(EntityType type, double rewardHealth , String bossName) {
        this.entityType = type;
        this.rewardHealth = rewardHealth;
        this.bossName = bossName;
    }

    public static BossType findByEntityType(EntityType entityType) {
        if (BooleanUtils.isFalse(CACHED_ITEM.containsKey(entityType)))
            return null;

        return CACHED_ITEM.get(entityType);
    }

    EntityType getEntityType() {
        return entityType;
    }

    public double getRewardHealth() {
        return rewardHealth;
    }

    public String getBossName() {
        return bossName;
    }
}