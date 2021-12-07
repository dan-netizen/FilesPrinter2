package com.higedata.fileprinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ConfigUtility {

    public static void saveSettingsToFile(ServiceContext serviceContext) {
        try (FileWriter fileWriter = new FileWriter(ConfigUtility.getConfigFile().toString());
             PrintWriter printWriter = new PrintWriter(fileWriter)){
            serviceContext.exportToList().forEach(printWriter::println);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     public static Path getConfigFile() {
        Path path = java.nio.file.Paths.get(System.getProperty("user.dir"));
        path = path.resolve("settings.cfg");
        return path;
    }



    /*
     * Utility class, no construction
     */
    private ConfigUtility() {}

}