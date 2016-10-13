package com.app.checkchat.services.authentication;

import com.app.checkchat.models.AuthenticationInfo;
import com.app.checkchat.models.User;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.beesightsoft.core.services.common.RestResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by MyPC on 14/8/2016.
 */
public interface RestAuthenticationServiceInterface {
    @POST("api/users/registerOrLoginExternal")
    Call<RestResponseMessage<AuthenticationInfo>> loginSocial(@Body LoginSocialRequest request);
    @GET("api/users/current")
    Call<RestResponseMessage<User>> getMe(@Header("Authorization") String accessToken);

}
