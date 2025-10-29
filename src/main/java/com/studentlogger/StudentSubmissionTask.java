package com.studentlogger;

/**
 * Represents a task to process a student's submission by writing their record to a score file.
 */
public class StudentSubmissionTask implements Runnable {
    private final StudentRecord studentRecord;
    private final ScoreFileHandler scoreFileWriter;

    public StudentSubmissionTask(StudentRecord studentRecord, ScoreFileHandler scoreFileWriter) {
        this.studentRecord = studentRecord;
        this.scoreFileWriter = scoreFileWriter;
    }

    @Override
    public void run() {
        scoreFileWriter.logRecord(studentRecord);
    }
}