package com.example.mvpdemo.data;

import android.content.Context;


import com.example.mvpdemo.data.network.ApiHelper;
import com.example.mvpdemo.data.network.model.LoginRequest;
import com.example.mvpdemo.data.network.model.LoginResponse;
import com.example.mvpdemo.data.prefs.PreferencesHelper;
import com.example.mvpdemo.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper
                          ) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
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
        return false;
    }

    @Override
    public void setCurrentUserLoggedInMode(boolean mode) {

    }

    @Override
    public Single<LoginResponse> getPostApiCall(LoginRequest request) {
        return mApiHelper.getPostApiCall(request);
    }



  /*  @Override
    public Single<LoginResponse> getLoginApi(LoginRequest request) {
        return mApiHelper.getLoginApi(request);
    }

    @Override
    public Single<List<PlaceListResponse>> getRoutListapi(PlaceListRequest request) {
        return mApiHelper.getRoutListapi(request);
    }

    @Override
    public Single<List<PersonResponse>> getPersonApiCall() {
        return mApiHelper.getPersonApiCall();
    }*/
}
