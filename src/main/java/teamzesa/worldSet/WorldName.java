package teamzesa.worldSet;

public enum WorldName {
    world("야생"),
    world_nether("지옥"),
    world_the_end("엔드");

    private final String exchangeString;

    WorldName(String worldName) {
        exchangeString = worldName;
    }

    public String getWorldName() {
        return exchangeString;
    }
}
