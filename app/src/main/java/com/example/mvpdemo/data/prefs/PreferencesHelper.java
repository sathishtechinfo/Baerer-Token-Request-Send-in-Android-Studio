package com.example.mvpdemo.data.prefs;

public interface PreferencesHelper {


    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    boolean getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(boolean mode);
}
