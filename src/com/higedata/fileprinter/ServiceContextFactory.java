package com.higedata.fileprinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class ServiceContextFactory {

    /**
     * Factory class that takes a String filename as parameter and returns a new ServiceContext object
     * containing the values found in the file
     * @param configFilePath A Path value representing the configuration file
     * @return An object of type ServiceContext
     */
    public static ServiceContext buildServiceContext(Path configFilePath) {
        ServiceContext serviceContext = new ServiceContext();
        try {
            Files.readAllLines(configFilePath).forEach(line -> parseConfigString(line, serviceContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviceContext;
    }

    /**
     * Method that builds a ServiceContext object based on the settings given as parameter
     * @param settings List of String containing settings data
     * @return An object of type ServiceContext
     */
    public static ServiceContext buildServiceContext(List<String> settings) {
        ServiceContext serviceContext = new ServiceContext();
        settings.forEach(setting -> parseConfigString(setting, serviceContext));
        return serviceContext;
    }

    /**
     * Method that builds and returns a ServiceContext object based on the String arguments given as varargs
     * Settings not in the varargs are set to default value
     * @param args Varargs of type String representing ServiceContext strings (e.g. "isDuplex=true")
     * @return An object of type ServiceContext
     */
    public static ServiceContext buildServiceContext(String... args) {
        ServiceContext serviceContext = new ServiceContext();
        Arrays.stream(args).iterator().forEachRemaining(arg -> parseConfigString(arg, serviceContext));
        return serviceContext;
    }

    /**
     * Method that extracts the key-value pair from the given String parameter
     * and modifies the corresponding value of the given ServiceContext reference
     * @param setting A String containing a key-value pair of properties (e.g. "numberCopies=2")
     * @param serviceContext A ServiceContext object reference containing printing properties to be updated
     */
    public static void parseConfigString(String setting, ServiceContext serviceContext) {
        //TODO if position < 0 then throw exception?
        int position = setting.indexOf("=");
        String key;
        String value;
        if (position > 0) {
            key = setting.substring(0, position).toLowerCase(Locale.ROOT);
            value = setting.substring(position + 1);
            switch (key) {
                case "isduplex":
                    serviceContext.setDuplex(Boolean.parseBoolean(value));
                    break;
                case "printer":
                    serviceContext.setPrinter(PrintUtility.findPrintService(value));
                    break;
                case "canprintpdf":
                    serviceContext.setCanPrintPDF(Boolean.parseBoolean(value));
                    break;
                case "filespath":
                    serviceContext.setFilesPath(Paths.get(value));
                    break;
                case "numbercopies":
                    serviceContext.setNumberCopies(Integer.parseInt(value));
                    break;
                case "printtimer":
                    serviceContext.setPrintTimer(Integer.parseInt(value));
                    break;
            }
        }
    }
}
