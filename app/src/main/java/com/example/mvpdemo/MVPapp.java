package com.example.mvpdemo;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.mvpdemo.di.component.ApplicationComponent;
import com.example.mvpdemo.di.component.DaggerApplicationComponent;
import com.example.mvpdemo.di.module.ApplicationModule;
import com.example.mvpdemo.utils.AppLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MVPapp extends Application {



    @Inject
    CalligraphyConfig mCalligraphyConfig;
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static MVPapp mInstance;


    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule (this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        Gson gson = new GsonBuilder ()
                .setLenient()
                .create();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new GsonParserFactory (gson));
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
        mInstance = this;
    }
    public static synchronized MVPapp getInstance() {
        return mInstance;
    }
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}