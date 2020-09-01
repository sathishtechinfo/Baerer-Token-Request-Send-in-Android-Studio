package com.example.mvpdemo.ui.LoginPage;


import com.example.mvpdemo.base.MvpView;
import com.example.mvpdemo.data.network.model.LoginResponse;


public interface LoginMvpView extends MvpView {
    void updatepost(LoginResponse loginResponse);

}
