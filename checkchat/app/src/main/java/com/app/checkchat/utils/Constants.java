package com.app.checkchat.utils;

/**
 * Created by MyPC on 15/8/2016.
 */
public class Constants {
    public static final String LOG_ENTRIES_API_KEY = "b5bb26ae-c73f-4144-a247-c982d596ca20";
    public static final String AUTH_UNIQUE_KEY = "user_atoms";

    public static final String STORAGE_CONNECTIONS_TRING = "DefaultEndpointsProtocol=https;"
            + "AccountName=atomsstorage;"
            + "AccountKey=ScUJiDuPyOGF/TdT62ULM77IrbXq5suXNiIKKqh5X1XQ82LXsmr9fMNJ6GCaPMTfMjYj2OEF8jDaHAikOBo6Pw==";

    public static final String CONTAINER_STORIES = "stories";

    public static final String CONTAINER_AVATAR = "avatars";

    public static final String ENDPOINT="https://atomsstorage.blob.core.windows.net/";



    public enum StoryCategory
    {
        LOVE,
        FAMILY,
        HEALTH,
        CAREER,
        LIFE;

        public int getCategory() {
            switch(this) {
                case LOVE: return 1;
                case FAMILY: return 2;
                case HEALTH: return 3;
                case CAREER: return 4;
                case LIFE: return 5;
            }
            return 1;
        }
    }

    public static String partIdToUrl(String photoId, String container){

        return ENDPOINT+container+"/"+photoId;
    }
}
