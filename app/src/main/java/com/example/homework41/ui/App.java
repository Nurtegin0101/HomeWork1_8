package com.example.homework41.ui;

import android.app.Application;

import com.example.homework41.ui.databaseRoom.AppDatabase;
import com.example.homework41.ui.utils.Prefs;

public class App extends Application {
    public static Prefs prefs;
    public static AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
        db = AppDatabase.getDbInstance(this);
    }
}
