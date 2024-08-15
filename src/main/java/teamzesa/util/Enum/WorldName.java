package teamzesa.util.Enum;

public enum WorldName {
    world("야생", "world"),
    world_nether("지옥", "world_nether"),
    world_the_end("엔드", "world_the_end");

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
