package com.example.homework41.ui.databaseRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.homework41.ui.form.FormModel;

@Database(entities = {FormModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "DB NAME")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
