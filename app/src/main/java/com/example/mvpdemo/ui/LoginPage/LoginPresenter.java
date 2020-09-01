package com.example.mvpdemo.ui.LoginPage;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.data.DataManager;
import com.example.mvpdemo.data.network.model.LoginRequest;
import com.example.mvpdemo.data.network.model.LoginResponse;
import com.example.mvpdemo.utils.rx.SchedulerProvider;
import com.google.gson.Gson;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onPostDetails(String email,String password) {
        getMvpView().showLoading();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        LoginRequest request=new LoginRequest ();
        request.setEmail(email);
        request.setPassword(password);

        getCompositeDisposable().add(getDataManager()
                .getPostApiCall(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse)
                            throws Exception {

                        if (loginResponse != null ) {
                            getMvpView().updatepost(loginResponse);
                        }

                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
