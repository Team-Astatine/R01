package teamzesa.IOHandler;


import teamzesa.R01;

import java.io.File;

public enum DataFile {
    CONFIG(new File(R01.getPlugin(R01.class).getDataFolder(),"config.yml")),
    USER_DATA(new File(R01.getPlugin(R01.class).getDataFolder(),"userData.json")),
    ABSOLUTE_PATH(new File(R01.getPlugin(R01.class).getDataFolder().getParentFile().getAbsolutePath()));
    private final File fileInstance;

    DataFile(File dataName) {
        fileInstance = dataName;
    }

    public File getFileInstance() {
        return fileInstance;
    }
}
