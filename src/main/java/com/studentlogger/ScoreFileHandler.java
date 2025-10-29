package com.studentlogger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ScoreFileHandler {
    private final String logFilePath;

    public ScoreFileHandler(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public synchronized void logRecord(StudentRecord record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(record.formatTranscript());
            writer.newLine();

            System.out.println(Thread.currentThread().getName() + " logged: " + record.formatTranscript());

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}