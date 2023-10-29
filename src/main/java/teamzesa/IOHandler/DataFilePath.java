package teamzesa.IOHandler;

import java.io.File;

public enum DataFilePath {
    USER_DATA("userData.json"),
    CONFIG("config.yml");
    private final String fileInstance;

    DataFilePath(String dataName) {
        fileInstance = dataName;
    }

    public String getFileInstance() {
        return fileInstance;
    }
}
