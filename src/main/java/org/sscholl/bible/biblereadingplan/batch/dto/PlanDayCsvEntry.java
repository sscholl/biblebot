package org.sscholl.bible.biblereadingplan.batch.dto;

public class PlanDayCsvEntry {

    private String input;
    private String date;
    private int dayNumber;
    private String text;
    private String passage1;
    private String passage2;
    private String passage3;
    private String passage4;
    private String passage5;
    private String passage6;
    private String passage7;
    private String passage8;
    private String passage9;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPassage1() {
        return passage1;
    }

    public void setPassage1(String passage1) {
        this.passage1 = passage1;
    }

    public String getPassage2() {
        return passage2;
    }

    public void setPassage2(String passage2) {
        this.passage2 = passage2;
    }

    public String getPassage3() {
        return passage3;
    }

    public void setPassage3(String passage3) {
        this.passage3 = passage3;
    }

    public String getPassage4() {
        return passage4;
    }

    public void setPassage4(String passage4) {
        this.passage4 = passage4;
    }

    public String getPassage5() {
        return passage5;
    }

    public void setPassage5(String passage5) {
        this.passage5 = passage5;
    }

    public String getPassage6() {
        return passage6;
    }

    public void setPassage6(String passage6) {
        this.passage6 = passage6;
    }

    public String getPassage7() {
        return passage7;
    }

    public void setPassage7(String passage7) {
        this.passage7 = passage7;
    }

    public String getPassage8() {
        return passage8;
    }

    public void setPassage8(String passage8) {
        this.passage8 = passage8;
    }

    public String getPassage9() {
        return passage9;
    }

    public void setPassage9(String passage9) {
        this.passage9 = passage9;
    }

    @Override
    public String toString() {
        return "PlanDayCsvEntry{" +
                "input='" + input + '\'' +
                ", date='" + date + '\'' +
                ", dayNumber='" + dayNumber + '\'' +
                ", text='" + text + '\'' +
                ", passage1='" + passage1 + '\'' +
                ", passage2='" + passage2 + '\'' +
                ", passage3='" + passage3 + '\'' +
                ", passage4='" + passage4 + '\'' +
                ", passage5='" + passage5 + '\'' +
                ", passage6='" + passage6 + '\'' +
                ", passage7='" + passage7 + '\'' +
                ", passage8='" + passage8 + '\'' +
                ", passage9='" + passage9 + '\'' +
                '}';
    }
}
