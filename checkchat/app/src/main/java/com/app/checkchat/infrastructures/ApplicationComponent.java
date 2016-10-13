package com.app.checkchat.infrastructures;


import com.app.checkchat.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MyPC on 7/27/2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class })
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}