package com.example.mvpdemo.di.component;

import android.app.Application;
import android.content.Context;


import com.example.mvpdemo.MVPapp;
import com.example.mvpdemo.data.DataManager;
import com.example.mvpdemo.di.ApplicationContext;
import com.example.mvpdemo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    Application getApplication();

    void inject(MVPapp app);

    DataManager getDataManager();
}
