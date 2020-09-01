package com.example.mvpdemo.ui.LoginPage;


import com.example.mvpdemo.base.MvpPresenter;

public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {

    void onPostDetails(String email, String password);

}
