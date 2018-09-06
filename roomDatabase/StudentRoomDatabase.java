package com.wildnet.mvvmroomexample.roomDatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.wildnet.mvvmroomexample.dao.StudentDao;
import com.wildnet.mvvmroomexample.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentRoomDatabase extends RoomDatabase {

    public abstract StudentDao stduentDao();
    private static StudentRoomDatabase INSTANCE;

   public static StudentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoomDatabase.class, "student_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StudentDao mDao;

        PopulateDbAsync(StudentRoomDatabase db) {
            mDao = db.stduentDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
           /* mDao.deleteAll();

            Student student = new Student("abc@gmail.com","dj","1234567890");
            mDao.insert(student);*/
            /*student = new Student("World");
            mDao.insert(student);*/
            return null;
        }
    }

}
