package com.app.checkchat.infrastructures;

import android.app.Application;
import android.content.Context;


import com.app.checkchat.ApiUrls;
import com.app.checkchat.services.authentication.AuthenticationService;
import com.app.checkchat.services.authentication.AuthenticationServiceInterface;
import com.app.checkchat.services.authentication.RestAuthenticationServiceInterface;
import com.app.checkchat.utils.Constants;
import com.beesightsoft.core.services.application.ApplicationService;
import com.beesightsoft.core.services.application.ApplicationServiceConfiguration;
import com.beesightsoft.core.services.application.ApplicationServiceConfigurationInterface;
import com.beesightsoft.core.services.application.ApplicationServiceInterface;
import com.beesightsoft.core.services.authentication.AuthenticationServiceConfiguration;
import com.beesightsoft.core.services.log.LogServiceInterface;
import com.beesightsoft.core.services.log.v0.LogService;
import com.beesightsoft.core.services.network.NetworkProvider;
import com.beesightsoft.core.services.network.NetworkProviderInterface;
import com.app.checkchat.utils.NetworkUtils;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 7/27/2016.
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    NetworkProviderInterface provideNetworkProvider(ApplicationServiceConfigurationInterface configuration) {
        Context context = this.application.getApplicationContext();
        return new NetworkProvider(context, configuration);
    }

    @Provides
    @Singleton
    ApplicationServiceInterface provideApplicationService(ApplicationServiceConfigurationInterface configuration) {
        ApplicationService applicationService = new ApplicationService(configuration);
        return applicationService;
    }

    @Provides
    @Singleton
    public ApplicationServiceConfigurationInterface provideApplicationServiceConfiguration(Application application) {
        LogServiceInterface logService = new LogService();
        try {
            logService.init(application.getApplicationContext(), Constants.LOG_ENTRIES_API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ApplicationServiceConfigurationInterface configuration = new ApplicationServiceConfiguration()
                .useLog(logService)
                .showToastOrDialog(true)
                .useToast(false);
        return configuration;
    }


    @Provides
    @Singleton
    public AuthenticationServiceInterface provideAuthenticationService(NetworkProviderInterface networkProvider) {
        RestAuthenticationServiceInterface restService = NetworkUtils.retrofitFor(ApiUrls.API_SERVER_ROOT_URL).create(RestAuthenticationServiceInterface.class);
        return new AuthenticationService(AuthenticationServiceConfiguration.init().useStorage(Constants.AUTH_UNIQUE_KEY), networkProvider, restService);
    }




}
