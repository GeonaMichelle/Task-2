package com.studentlogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExamScoreLoggerApp {

    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        System.out.println("=== Student Exam Score Logger Started ===");

        ScoreFileHandler scoreFileHandler = new ScoreFileHandler("scores.txt");

        StudentRecord[] studentRecords = {
                new StudentRecord("Aarav", 101, 88.5),
                new StudentRecord("Diya", 102, 91.0),
                new StudentRecord("Rohan", 103, 77.4),
                new StudentRecord("Meera", 104, 84.2)
        };

        ExecutorService loggingExecutor = Executors.newFixedThreadPool(NUM_THREADS);

        for (StudentRecord record : studentRecords) {
            loggingExecutor.execute(new StudentSubmissionTask(record, scoreFileHandler));
        }

        shutdownAndAwaitTermination(loggingExecutor);

        System.out.println("=== All scores successfully logged! ===");
    }


    private static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
    }
}