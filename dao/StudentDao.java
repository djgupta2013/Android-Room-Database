package com.wildnet.mvvmroomexample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wildnet.mvvmroomexample.model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("DELETE FROM student_data")
    void deleteAll();

    @Delete
    void deleteUser(Student studentEmail);

    /*@Query("DELETE FROM student_data WHERE s_email= :studentEmail")
    void deleteUser(String studentEmail);*/

    @Query("SELECT * from student_data ORDER BY s_email ASC")
    LiveData<List<Student>> getAllStudents();
}
