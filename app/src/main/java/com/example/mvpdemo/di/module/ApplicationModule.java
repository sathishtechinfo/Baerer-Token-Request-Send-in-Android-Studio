package com.example.mvpdemo.di.module;

import android.app.Application;
import android.content.Context;


import com.example.mvpdemo.R;
import com.example.mvpdemo.data.AppDataManager;
import com.example.mvpdemo.data.DataManager;
import com.example.mvpdemo.data.network.ApiHelper;
import com.example.mvpdemo.data.network.AppApiHelper;
import com.example.mvpdemo.data.prefs.AppPreferencesHelper;
import com.example.mvpdemo.data.prefs.PreferencesHelper;
import com.example.mvpdemo.di.ApplicationContext;
import com.example.mvpdemo.di.PreferenceInfo;
import com.example.mvpdemo.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
 public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

   /* @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(String userName
                                                           ) {
        return new ApiHeader.ProtectedApiHeader(
                userName
                );
    }*/


   /* @Provides
    @Singleton
    ApiHeader.PublicApiHeader providePublicAPiHeader(@ApiInfo String userName)
    {
        return new ApiHeader.PublicApiHeader(userName);
    }*/



    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/tauriregular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

}
