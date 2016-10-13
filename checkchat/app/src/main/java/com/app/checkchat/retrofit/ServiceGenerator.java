package com.app.checkchat.retrofit;

import com.app.checkchat.ApiUrls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tam on 17/08/2016.
 */
public class ServiceGenerator {

    public ServiceGenerator() {
    }


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(ApiUrls.API_SERVER_ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create());
    // basic
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
