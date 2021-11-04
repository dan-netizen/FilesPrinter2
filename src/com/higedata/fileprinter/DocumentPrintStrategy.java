package com.higedata.fileprinter;

interface DocumentPrintStrategy {
    void printDocument(String documentPath, java.util.ArrayList<String> settings);
}
