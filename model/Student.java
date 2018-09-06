package com.wildnet.mvvmroomexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "student_data")
public class Student {
    @PrimaryKey
    @ColumnInfo(name = "s_email")
    @NonNull
    private String studentEmail;


    @ColumnInfo(name = "s_name")
    private String studentName;


    @ColumnInfo(name = "s_password")
    private String studentPassword;

   public Student(java.lang.String email, java.lang.String name, java.lang.String password) {
        studentEmail = email;
        studentName = name;
        studentPassword = password;
    }
    public Student(){

    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
