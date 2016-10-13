package com.app.checkchat.services.authentication;

import com.app.checkchat.models.AuthenticationInfo;
import com.app.checkchat.models.User;
import com.app.checkchat.models.requests.LoginRequest;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.beesightsoft.core.services.authentication.AuthenticationServiceConfiguration;
import com.beesightsoft.core.services.authentication.v0.BaseAuthenticationService;
import com.beesightsoft.core.services.common.RestResponseMessage;
import com.beesightsoft.core.services.network.NetworkProviderInterface;

import bolts.Task;
import retrofit2.Call;

/**
 * Created by MyPC on 14/8/2016.
 */
public class AuthenticationService extends BaseAuthenticationService<User, LoginRequest, LoginSocialRequest> implements AuthenticationServiceInterface {

    private NetworkProviderInterface networkProvider;
    private RestAuthenticationServiceInterface restAuthenticationService;

    public AuthenticationService(AuthenticationServiceConfiguration configuration, NetworkProviderInterface networkProvider, RestAuthenticationServiceInterface restService) {
        super(configuration);
        this.networkProvider = networkProvider;
        this.restAuthenticationService = restService;
    }

    @Override
    protected User onLogin(LoginRequest aVoid) throws Exception {
        return null;
    }


    @Override
    protected User onLoginSocial(LoginSocialRequest loginSocialRequest) throws Exception {
        Call<RestResponseMessage<AuthenticationInfo>> call = restAuthenticationService.loginSocial(loginSocialRequest);
        AuthenticationInfo authenticationInfo = networkProvider.executeDefaultResultCall(call);

        Call<RestResponseMessage<User>> callUser = restAuthenticationService.getMe(authenticationInfo.createAccessToken());
        User user = networkProvider.executeDefaultResultCall(callUser);
        user.setAuthenticationInfo(authenticationInfo);

        return user;
    }

    @Override
    protected boolean onLogout() {
        return false;
    }

    @Override
    public Task<User> getMe() {
        Task<User> task = null;
        try {
            Call<RestResponseMessage<User>> callUser = restAuthenticationService.getMe(getCurrentUser().getAuthenticationInfo().createAccessToken());
            User user = networkProvider.executeDefaultResultCall(callUser);
            task = Task.forResult(user);
        }catch (Exception ex){
            task = Task.forError(ex);
        }
        return task;
    }


}
