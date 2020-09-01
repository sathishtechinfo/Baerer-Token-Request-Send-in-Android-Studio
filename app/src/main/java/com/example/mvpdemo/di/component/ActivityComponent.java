package com.example.mvpdemo.di.component;




import com.example.mvpdemo.di.PerActivity;
import com.example.mvpdemo.di.module.ActivityModule;
import com.example.mvpdemo.ui.LoginPage.LoginActivity;


import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(LoginActivity activity);


}
