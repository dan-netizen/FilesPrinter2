package com.higedata.fileprinter;

import javax.print.PrintService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceContext implements Cloneable {

    /**
     * Returns a new ServiceContext object identical to the current one.
     *
     * @return a new ServiceContext object
     */
    @Override
    public Object clone() {
        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setCanPrintPDF(isCanPrintPDF());
        serviceContext.setPrinter(getPrinter());
        serviceContext.setPrintTimer(getPrintTimer());
        serviceContext.setFilesPath(getFilesPath());
        serviceContext.setDuplex(isDuplex());
        serviceContext.setNumberCopies(getNumberCopies());

        return serviceContext;
    }

    public List<String> exportToList() {
        List<String> settings = new ArrayList<String>();
        settings.add("printer=" + getPrinter().toString());
        settings.add("printTimer=" + getPrintTimer());
        settings.add("filesPath=" + getFilesPath().toString());
        settings.add("numberCopies=" + getNumberCopies());
        settings.add("isDuplex=" + isDuplex());
        settings.add("canPrintPDF=" + isCanPrintPDF());

        return settings;
    }

    public PrintService getPrinter() {
        return printer;
    }

    public void setPrinter(PrintService printer) {
        this.printer = printer;
    }

    public int getPrintTimer() {
        return printTimer;
    }

    public void setPrintTimer(int printTimer) {
        this.printTimer = printTimer;
    }

    public Path getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(Path filesPath) {
        this.filesPath = filesPath;
    }

    public boolean isCanPrintPDF() {
        return canPrintPDF;
    }

    public void setCanPrintPDF(boolean canPrintPDF) {
        this.canPrintPDF = canPrintPDF;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    public boolean isDuplex() {
        return isDuplex;
    }

    public void setDuplex(boolean duplex) {
        isDuplex = duplex;
    }

    public void ServiceContext() {
        this.printer = PrintUtility.findPrintService(PrintUtility.getPrinterServiceNameList().get(0));
        this.printTimer = 15;
        this.filesPath = Paths.get(System.getProperty("user.dir")).resolve("\\ToPrint");
        this.canPrintPDF = false;
        this.numberCopies = 1;
        this.isDuplex = true;
    }


    /*
     * Parameters, initiated
     */
    private PrintService printer;
    private int printTimer;
    private Path filesPath;
    private boolean canPrintPDF;
    private int numberCopies;
    private boolean isDuplex;

}
