package com.example.answercubeproto;

public class StudentInfoClass {
    public String schoolName;
    public String majorName;
    public String className;

    public StudentInfoClass(String schoolName, String majorName, String className) {
        this.schoolName = schoolName;
        this.majorName = majorName;
        this.className = className;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
