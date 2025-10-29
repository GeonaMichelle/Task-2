package com.studentlogger;

public class StudentRecord {
    private final String studentName;
    private final int studentId;
    private final double examScore;

    public StudentRecord(String Name, int Id, double examScore) {
        this.studentName = Name;
        this.studentId = Id;
        this.examScore = examScore;
    }

    public String formatTranscript() {
        return String.format("%s | %d | %.2f", studentName, studentId, examScore);
    }
}