package com.example.homework41.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;
    private Context context;
    /*
    private static Prefs instance;
*/
/*    public static Prefs getInstance() {
        return instance;
    }*/

    public Prefs(Context context) {
        this.preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
    preferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("isShown", false);
    }

    public void save(String imgUri) {
        preferences.edit().putString("imgUrl", imgUri).apply();
    }

    public Uri getSave() {
        return Uri.parse(preferences.getString("imgUrl", ""));

    }

    public void delete() {
        preferences.edit().remove("imgUrl").apply();
    }
}
