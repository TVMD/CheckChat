package com.app.checkchat.activities;

import android.os.Bundle;

import com.app.checkchat.R;
import com.app.checkchat.services.authentication.AuthenticationServiceInterface;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Inject
    AuthenticationServiceInterface authenticationService;



    @Override
    public void init() {
        application.getApplicationComponent().inject(this);
        showSnackbar((authenticationService != null) +"");

    }
}
