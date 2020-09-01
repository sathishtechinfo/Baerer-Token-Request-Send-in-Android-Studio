package com.example.mvpdemo.data.network;





import com.example.mvpdemo.data.network.model.LoginRequest;
import com.example.mvpdemo.data.network.model.LoginResponse;

import java.util.List;

import io.reactivex.Single;

public interface ApiHelper {
    Single<LoginResponse> getPostApiCall(LoginRequest request);



}
