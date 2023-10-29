package teamzesa.IOHandler;

public enum DataFilePath {
    USER_DATA("userData.json"),
    CONFIG("config.yml");
    private final String fileName;

    DataFilePath(String dataName) {
        fileName = dataName;
    }

    public String getFileName() {
        return fileName;
    }
}
