package com.app.checkchat;

/**
 * Created by MyPC on 7/27/2016.
 */

public class ApiUrls {
    public static final String API_SERVER_ROOT_URL = BuildConfig.FLAVOR.equals("dev") ? ApiUrls.DEV_SERVER_URL :
            BuildConfig.FLAVOR.equals("staging") ? ApiUrls.STAGING_SERVER_URL :
                    BuildConfig.FLAVOR.equals("production") ? ApiUrls.PRODUCTION_SERVER_URL : null;

    private static final String DEV_SERVER_URL = "http://192.168.56.1:3000/";
    private static final String STAGING_SERVER_URL = "http://192.168.56.1:3000/";
    private static final String PRODUCTION_SERVER_URL = "http://192.168.56.1:3000/";
}
