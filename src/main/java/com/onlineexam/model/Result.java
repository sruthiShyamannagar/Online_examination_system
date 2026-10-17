package com.onlineexam.model;

public class Result {
    private final int score;
    private final int total;
    private final double percentage;

    public Result(int score, int total, double percentage) {
        this.score = score;
        this.total = total;
        this.percentage = percentage;
    }

    public int getScore() { return score; }
    public int getTotal() { return total; }
    public double getPercentage() { return percentage; }
}
