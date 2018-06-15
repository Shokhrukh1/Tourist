package co.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Crish on 28.12.2017.
 */

public class PreferencesHelper {
    public static final String LANGUAGE = "LANGUAGE";
    public static final String USER_ID = "USER_ID";

    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLanguage(int langId) {
        sharedPreferences
                .edit()
                .putInt(LANGUAGE, langId)
                .apply();
    }

    public int getLanguage() {
        return sharedPreferences.getInt(LANGUAGE, 2);
    }

    public boolean containsLanguage() {
        return sharedPreferences.contains(LANGUAGE);
    }

    public void setUserId(long userId) {
        sharedPreferences
                .edit()
                .putLong(USER_ID, userId)
                .apply();
    }

    public long getUserId() {
        return sharedPreferences.getLong(USER_ID, -1);
    }

    public boolean containsUserId() {
        return sharedPreferences.contains(USER_ID);
    }

    public void removeUserId(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .remove(USER_ID)
                .apply();
    }
}
