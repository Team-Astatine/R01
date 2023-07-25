package teamzesa.IOHandler;

public enum DataFile {
    USER_DATA("userData.json"),
    CONFIG("config.yml");

    private final String fileName;

    DataFile(String dataName) {
        fileName = dataName;
    }

    public String getFileName() {
        return fileName;
    }
}
