package com.app.checkchat.services.demo;

import com.app.checkchat.models.AuthenticationInfo;
import com.app.checkchat.models.Demo;
import com.app.checkchat.models.User;
import com.app.checkchat.models.Wraper;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.beesightsoft.core.services.common.RestResponseMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MyPC on 14/8/2016.
 */
public interface RestDemoServiceInterface {
    @GET("api/data")
    Call<RestResponseMessage<User>> getStack();


}
