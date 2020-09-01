package com.example.mvpdemo.data.network;


import com.example.mvpdemo.data.network.model.LoginRequest;
import com.example.mvpdemo.data.network.model.LoginResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }



    @Override
    public Single<LoginResponse> getPostApiCall(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN).addJSONObjectBody(request.toJSON()).
                build().getObjectSingle(LoginResponse.class);
    }
}

