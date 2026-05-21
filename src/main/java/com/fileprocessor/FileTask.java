package com.fileprocessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTask implements Runnable{
    private final File file;
    private final ReportWriter reportWriter;
    String fileName;
    int lineCount = 0;
    int wordCount = 0;
    int characterCount = 0;
    public FileTask(File file, ReportWriter reportWriter) {
        this.file = file;
        this.reportWriter = reportWriter;
        this.fileName = file.getName();
    }
     private void countFileContent() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineCount++;
            characterCount += line.length();
            wordCount += line.split("\\s+").length;
        }
         scanner.close();
    }
    public int getWordCount() {
        return wordCount;
    }
    public int getLineCount() {
        return lineCount;
    }
    public int getCharacterCount() {
        return characterCount;
    }
    public String getFileName() {
        return fileName;
    }
@Override
    public void run() {
        try{
            countFileContent();
            reportWriter.writeResult(this);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

}

}
