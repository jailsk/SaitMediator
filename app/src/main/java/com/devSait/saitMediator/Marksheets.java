package com.devSait.saitMediator;

public class Marksheets {
    String subjectName,marks;

    public Marksheets(){

    }

    public Marksheets(String subjectName, String marks) {
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getMarks() {
        return marks;
    }
}
