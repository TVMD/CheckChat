package com.app.checkchat.retrofit;

/**
 * Created by Tam on 17/08/2016.
 */
public class RetrofitService {

    private RetrofitInterface retrofitInterface;

    public RetrofitService (RetrofitInterface retrofitServiceInterface) {
        this.retrofitInterface = retrofitServiceInterface;
        this.retrofitInterface=ServiceGenerator.createService(RetrofitInterface.class);
    }
}
