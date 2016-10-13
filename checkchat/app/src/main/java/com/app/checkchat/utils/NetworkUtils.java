package com.app.checkchat.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.beesightsoft.core.services.common.JodaDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MyPC on 7/27/2016.
 */

public class NetworkUtils {

    private static Gson gson;

    public static Retrofit retrofitFor(String baseUrl) {
        //gson
        gson = createBuilder().create();

        //client
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
                                   @Override
                                   public Response intercept(Chain chain) throws IOException {
                                       Request request = chain.request().newBuilder()
                                               .addHeader("Accept", "application/vnd.app.atoms.mobile-v1+json").build();

                                       Response response = chain.proceed(request);
                                       int code = response.code();
                                       if (code == 401) {
                                       }

                                       return response;
                                   }
                               });


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(loggingInterceptor);


        OkHttpClient okHttpClient = builder.build();
        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }

    private static GsonBuilder gsonBuilder = null;
    public static GsonBuilder createBuilder() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(new TypeToken<DateTime>() {}.getType(), new JodaDateTimeDeserializer());
           // gsonBuilder.registerTypeAdapter(new TypeToken<List<Address>>() {}.getType(), new AddressesSerialized());
        }

        return gsonBuilder;
    }


    public static Gson getGson() {
        if (gson == null)
            gson = createBuilder().create();
        return gson;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
