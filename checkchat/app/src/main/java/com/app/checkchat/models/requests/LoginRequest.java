package com.app.checkchat.models.requests;

import com.beesightsoft.core.services.authentication.LoginRequestInterface;

/**
 * Created by MyPC on 7/27/2016.
 */

public class LoginRequest extends BaseRequest implements LoginRequestInterface {


    //region Login Interface
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String password) {

    }
    //endregion

}
