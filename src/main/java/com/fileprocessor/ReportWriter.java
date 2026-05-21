package com.fileprocessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReportWriter {
    private final Lock lock = new ReentrantLock();
    private final BufferedWriter writer;

    public ReportWriter(String timestamp) throws IOException {
        new File("output").mkdirs();
        writer = new BufferedWriter(new FileWriter("output/report_" + timestamp + ".txt"));

    }

    public void writeResult(FileTask task){
        lock.lock();
        try{
            writer.write("File: " + task.getFileName());
            writer.write(" | Lines: " + task.getLineCount());
            writer.write(" | Words: " + task.getWordCount());
            writer.write(" | Characters: " + task.getCharacterCount());
            writer.newLine();
        }
        catch(IOException e){
            System.out.println("Could not write the result");
        }
        finally{
            lock.unlock();
        }
    }
    public void close() throws IOException{
        writer.close();
    }

    }
