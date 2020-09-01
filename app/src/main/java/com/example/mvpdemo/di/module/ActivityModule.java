package com.example.mvpdemo.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.mvpdemo.di.ActivityContext;
import com.example.mvpdemo.di.PerActivity;

import com.example.mvpdemo.ui.LoginPage.LoginMvpPresenter;
import com.example.mvpdemo.ui.LoginPage.LoginMvpView;
import com.example.mvpdemo.ui.LoginPage.LoginPresenter;

import com.example.mvpdemo.utils.rx.AppSchedulerProvider;
import com.example.mvpdemo.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideScheduleProvider() {
        return new AppSchedulerProvider ();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager (activity);
    }


    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> providesLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }


}
