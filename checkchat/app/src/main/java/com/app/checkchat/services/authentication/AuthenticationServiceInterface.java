package com.app.checkchat.services.authentication;

import com.app.checkchat.models.User;
import com.app.checkchat.models.requests.LoginRequest;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.beesightsoft.core.services.authentication.BaseAuthenticationServiceInterface;

import java.util.List;

import bolts.Task;

/**
 * Created by MyPC on 14/8/2016.
 */
public interface AuthenticationServiceInterface extends BaseAuthenticationServiceInterface<User, LoginRequest, LoginSocialRequest>   {
    Task<User> getMe();

}
