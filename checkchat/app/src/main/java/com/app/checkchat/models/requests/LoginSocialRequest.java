package com.app.checkchat.models.requests;

import com.beesightsoft.core.services.authentication.LoginSocialRequestInterface;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 7/27/2016.
 */

public class LoginSocialRequest extends BaseRequest implements LoginSocialRequestInterface{

    public LoginSocialRequest(){}

    public LoginSocialRequest(String fbToken){
        this.accessToken = fbToken;
    }


    @SerializedName("externalAccessToken")
    private String accessToken;

    @SerializedName("provider")
    private String platform = "facebook";

    //region Getter Setter
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    //endregion
}
