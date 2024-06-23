package teamzesa.util.Enum;


import teamzesa.R01;

import java.io.File;

public enum DataFile {
    CONFIG(new File(R01.getPlugin(R01.class).getDataFolder(), "config.yml"), "Config"),
    USER_DATA(new File(R01.getPlugin(R01.class).getDataFolder(), "userData.json"), "User Data"),
    KILL_STATUS(new File(R01.getPlugin(R01.class).getDataFolder(), "killStatus.json"), "Kill Status "),
    ABSOLUTE_PATH(new File(R01.getPlugin(R01.class).getDataFolder().getParentFile().getAbsolutePath()), "Plugin Folder");

    private final File fileInstance;
    private final String fileTypeName;

    DataFile(File fileInstance, String fileTypeName) {
        this.fileInstance = fileInstance;
        this.fileTypeName = fileTypeName;
    }

    public File getFileInstance() {
        return fileInstance;
    }

    public String getFileName() {
        return fileInstance.getName();
    }

    public String getFileTypeName() {
        return fileTypeName;
    }
}
