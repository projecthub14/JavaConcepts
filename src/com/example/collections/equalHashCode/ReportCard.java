package com.example.collections.equalHashCode;

public class ReportCard {

    private int mathScore;

    public ReportCard() {
        this.mathScore = (int)Math.random()* 101 + 1;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }
}
