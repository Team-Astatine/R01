package teamzesa;

public enum DataFile {
    USER_DATA("userData.json"),
    CONFIG("config.yml");

    private final String fileName;

    DataFile(String dataName) {
        fileName = dataName;
    }

    public String getDataFileName() {
        return fileName;
    }
}
