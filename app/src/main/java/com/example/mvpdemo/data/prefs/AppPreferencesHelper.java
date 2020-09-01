package com.example.mvpdemo.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.mvpdemo.di.ApplicationContext;
import com.example.mvpdemo.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_FIRST_TIME = "PREF_KEY_USER_LOGGED_IN_MODE";
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        return null;
    }

    @Override
    public void setCurrentUserId(Long userId) {

    }

    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public void setCurrentUserName(String userName) {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void setCurrentUserEmail(String email) {

    }

    @Override
    public boolean getCurrentUserLoggedInMode() {
        return mPrefs.getBoolean(PREF_KEY_USER_FIRST_TIME, false);
    }

    @Override
    public void setCurrentUserLoggedInMode(boolean mode) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_FIRST_TIME, true).apply();
    }
}
