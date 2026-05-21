package com.fileprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
 static void main(String[] args) throws IOException, InterruptedException {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
    ReportWriter reportWriter = new ReportWriter(timestamp);
    Path path = Paths.get("input");
    File[] files = path.toFile().listFiles((_, name) -> name.endsWith(".txt"));
    if (files == null || files.length == 0){
        System.err.println("No files found in input folder");
        return;
    }
    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    for (File file : files){
        FileTask task = new FileTask(file, reportWriter);
        executorService.submit(task);
    }
    executorService.shutdown();
    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

    File processedBase = new File("processed/" + timestamp);
    processedBase.mkdirs();

    for(File file : files){
        File destination = new File(processedBase, file.getName());
        file.renameTo(destination);
    }
    reportWriter.close();
     System.out.println("Processing completed");
}


}
