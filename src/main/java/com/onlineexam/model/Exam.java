package com.onlineexam.model;

public class Exam {
    private final int examId;
    private final String examName;
    private final String subject;
    private final int durationMinutes;

    public Exam(int examId, String examName, String subject, int durationMinutes) {
        this.examId = examId;
        this.examName = examName;
        this.subject = subject;
        this.durationMinutes = durationMinutes;
    }

    public int getExamId() { return examId; }
    public String getExamName() { return examName; }
    public String getSubject() { return subject; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String toString() {
        return examName + " | " + subject + " | " + durationMinutes + " min";
    }
}
