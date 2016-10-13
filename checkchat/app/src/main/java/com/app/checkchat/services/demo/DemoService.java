package com.app.checkchat.services.demo;

import com.app.checkchat.models.AuthenticationInfo;
import com.app.checkchat.models.Demo;
import com.app.checkchat.models.User;
import com.app.checkchat.models.Wraper;
import com.app.checkchat.models.requests.LoginRequest;
import com.app.checkchat.models.requests.LoginSocialRequest;
import com.app.checkchat.services.authentication.AuthenticationServiceInterface;
import com.beesightsoft.core.services.authentication.AuthenticationServiceConfiguration;
import com.beesightsoft.core.services.authentication.v0.BaseAuthenticationService;
import com.beesightsoft.core.services.common.RestResponseMessage;
import com.beesightsoft.core.services.network.NetworkProviderInterface;

import java.util.ArrayList;
import java.util.List;

import bolts.Task;
import retrofit2.Call;

/**
 * Created by MyPC on 14/8/2016.
 */
public class DemoService  implements DemoServiceInterface {

    private NetworkProviderInterface networkProvider;
    private RestDemoServiceInterface restDemoService;
    private AuthenticationServiceInterface  authenticationService;;

    public DemoService(NetworkProviderInterface networkProvider, RestDemoServiceInterface restService, AuthenticationServiceInterface authenticationService) {
        this.networkProvider = networkProvider;
        this.restDemoService = restService;
        this.authenticationService = authenticationService;
    }



    @Override
    public Task<List<User>> getStack(String tags) {
        Task<List<User>> task = null;
        try {
            Call<RestResponseMessage<User>> callDemo = restDemoService.getStack();
            List<User> demo =new ArrayList<>();
            User user       = networkProvider.executeDefaultResultCall(callDemo);
            demo.add(user);
            task = Task.forResult(demo);
        }catch (Exception ex){
            task = Task.forError(ex);
        }
        return task;
    }


}
