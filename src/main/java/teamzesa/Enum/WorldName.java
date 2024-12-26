package teamzesa.Enum;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WorldName {
    WORLD("야생", "world"),
    WORLD_NETHER("지옥", "world_nether"),
    WORLD_THE_END("엔드", "world_the_end");

    private final String exchangeKorean;
    private final String exchangeEnglish;
    private final static Map<String, WorldName> CACHED_ITEM = Arrays.stream(values())
            .collect(Collectors.toMap(WorldName::getExchangeEnglish, Function.identity()));

    WorldName(String korean, String english) {
        exchangeKorean = korean;
        exchangeEnglish = english;
    }

    public static WorldName findByWorldName(String worldName) {
        return CACHED_ITEM.get(worldName);
    }

    public String getExchangeEnglish() {
        return exchangeEnglish;
    }

    public String getKoreanWorldName() {
        return exchangeKorean;
    }
}
