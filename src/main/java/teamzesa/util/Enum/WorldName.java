package teamzesa.util.Enum;

public enum WorldName {
    WORLD("야생", "world"),
    WORLD_NETHER("지옥", "world_nether"),
    WORLD_THE_END("엔드", "world_the_end");

    private final String exchangeKorean;
    private final String exchangeEnglish;

    WorldName(String korean, String english) {
        exchangeKorean = korean;
        exchangeEnglish = english;
    }

    public String getExchangeEnglish() {
        return exchangeEnglish;
    }

    public String getKoreanWorldName() {
        return exchangeKorean;
    }
}
