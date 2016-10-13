package com.app.checkchat;

import android.support.multidex.MultiDexApplication;

import com.app.checkchat.infrastructures.ApplicationComponent;
import com.app.checkchat.infrastructures.ApplicationModule;
import com.app.checkchat.infrastructures.DaggerApplicationComponent;
import com.app.checkchat.utils.Constants;
import com.app.checkchat.utils.Utils;
import com.facebook.FacebookSdk;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import net.danlew.android.joda.JodaTimeAndroid;

import org.androidannotations.annotations.EApplication;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by MyPC on 14/8/2016.
 */
@EApplication
public class CustomApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        Utils.init(this);
        JodaTimeAndroid.init(this);
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this.getApplicationContext()))
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig());
        this.applicationComponent = daggerApplicationComponentBuilder().build();

       /* CloudStorageAccount storageAccount = null;
        try {
            storageAccount = CloudStorageAccount.parse(Constants.STORAGE_CONNECTIONS_TRING);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference(Constants.CONTAINER_STORIES);
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();

// Include public access in the permissions object.
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);

// Set the permissions on the container.
            container.uploadPermissions(containerPermissions);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();*/

        //getHashKey();
    }

    /*private void getHashKey(){
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }*/


    protected CalligraphyConfig calligraphyConfig() {
        CalligraphyConfig calligraphyConfig = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        return calligraphyConfig;
    }

    protected DaggerApplicationComponent.Builder daggerApplicationComponentBuilder(){
        DaggerApplicationComponent.Builder daggerApplicationComponentBuilder = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this));
        return daggerApplicationComponentBuilder;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
