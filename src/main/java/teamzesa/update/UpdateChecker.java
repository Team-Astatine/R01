package teamzesa.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class UpdateChecker {
    private static class UpdateCheckerHolder {
        private static UpdateChecker INSTANCE = new UpdateChecker();
    }

    private static double gitVersion;
    private static double localVersion;
    private static File folder;

    public static UpdateChecker getUpdateChecker() {
        return UpdateCheckerHolder.INSTANCE;
    }

    public void fileLoader(File inFile) {
        folder = inFile;
    }

    public void fileManager() {
        gitUpdateCheck();
        localPluginCheck();

        if (gitVersion == localVersion)
            return;
        if (gitVersion > localVersion) {
            removeLegacyPlugin();
            installNewPlugin();
        }
    }

    private void installNewPlugin() {
        String gitVersionStr = Double.toString(gitVersion); // gitVersion을 문자열로 변환
        String downloadLink = "https://github.com/JAXPLE/R01/releases/download/" + gitVersionStr + "/R01-" + gitVersionStr + ".jar";


        //update 예정
    }

    private void removeLegacyPlugin() {
        Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().contains(".jar") && file.getName().contains("R01"))
                .forEach(File::deleteOnExit);
    }

    private void localPluginCheck() {
        Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().contains(".jar") && file.getName().contains("R01"))
                .forEach(file -> localVersion = Double.parseDouble(file.getName()
                        .replace("R01-","")
                        .replace(".jar","")
                        )
                );
    }

    private void gitUpdateCheck() {
        try {
            String line;
            URL url = new URL("https://github.com/JAXPLE/R01/releases/latest");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = br.readLine()) != null) {
                if (line.contains("<title>Release")) {
                    gitVersion = Double.parseDouble(
                            line.split("<title>Release Astatine ")[1]
                                .split(" ·")[0]
                    );
                    br.close();
                    return;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
