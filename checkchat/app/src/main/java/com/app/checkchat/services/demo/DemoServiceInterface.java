package com.app.checkchat.services.demo;

import com.app.checkchat.models.Demo;
import com.app.checkchat.models.User;
import com.app.checkchat.models.requests.LoginRequest;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.beesightsoft.core.services.authentication.BaseAuthenticationServiceInterface;

import java.util.List;

import bolts.Task;
import retrofit2.http.Query;

/**
 * Created by MyPC on 14/8/2016.
 */
public interface DemoServiceInterface   {
    Task<List<User>> getStack(String tags);

}
