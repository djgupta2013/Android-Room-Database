package com.wildnet.mvvmroomexample.viewModel;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.wildnet.mvvmroomexample.model.Student;
import com.wildnet.mvvmroomexample.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository mRepository;
    private LiveData<List<Student>> mAllStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        mRepository=new StudentRepository(application);
        mAllStudent=mRepository.getmAllStudents();
    }

   public LiveData<List<Student>> getAllStudents(){
        return mAllStudent;
    }

    public void deleteUser(Student student){
        mRepository.deleteUser(student);
    }

    public void insert(Student student){
        mRepository.insert(student);
    }



}
