package com.wildnet.mvvmroomexample.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.wildnet.mvvmroomexample.dao.StudentDao;
import com.wildnet.mvvmroomexample.model.Student;
import com.wildnet.mvvmroomexample.roomDatabase.StudentRoomDatabase;

import java.util.List;

public class StudentRepository {
    private StudentDao mStudentDao;
    private LiveData<List<Student>> mAllStudents;
    public StudentRepository(){}

    public StudentRepository(Application application){
        StudentRoomDatabase database= StudentRoomDatabase.getDatabase(application);
        mStudentDao= database.stduentDao();
        mAllStudents=mStudentDao.getAllStudents();
    }

   public LiveData<List<Student>> getmAllStudents() {
        return mAllStudents;
    }

    public void insert(Student student){
        new insertAsyncTask(mStudentDao).execute(student);
    }

    public void deleteUser(Student student){
        new deleteUserAsyncTask(mStudentDao).execute(student);
    }

    //insert data in Room database
    private static class insertAsyncTask extends AsyncTask<Student, Void, Void>{
        private StudentDao mAsyncTaskDao;

        insertAsyncTask(StudentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... students) {
            mAsyncTaskDao.insert(students[0]);
            return null;
        }
    }

    //delete data to Room Database
    private static class deleteUserAsyncTask extends AsyncTask<Student, Void, Void>{
        private StudentDao mAsyncTaskDao;

        deleteUserAsyncTask(StudentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... students) {
            mAsyncTaskDao.deleteUser(students[0]);
            return null;
        }
    }
}
