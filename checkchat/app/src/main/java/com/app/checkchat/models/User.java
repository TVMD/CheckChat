package com.app.checkchat.models;

import com.beesightsoft.core.services.authentication.LoginResponseInterface;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 15/8/2016.
 */
public class User extends GuidBaseEntity implements LoginResponseInterface{


    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("avatarId")
    private String avatarId;
    @SerializedName("coverPhotoId")
    private String coverPhotoId;
    @SerializedName("star")
    private double star;
    @SerializedName("donated")
    private double donated;
    @SerializedName("volunteer")
    private int volunteer;
    @SerializedName("heartInJar")
    private int heartInJar;
    @SerializedName("starInDarkSky")
    private int starInDarkSky;
    @SerializedName("about")
    private String about;
    @SerializedName("whatCanIHelp")
    private String whatCanIHelp;
    @SerializedName("specializations")
    private List<String> specializations = new ArrayList<>();


    private AuthenticationInfo authenticationInfo;

    public String getCoverPhotoId() {
        return coverPhotoId;
    }

    public void setCoverPhotoId(String coverPhotoId) {
        this.coverPhotoId = coverPhotoId;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public DateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    public double getDonated() {
        return donated;
    }

    @Override
    public DateTime getUpdatedAt() {
        return super.getUpdatedAt();
    }

    public double getStar() {
        return star;
    }

    public int getHeartInJar() {
        return heartInJar;
    }

    public int getStarInDarkSky() {
        return starInDarkSky;
    }

    public int getVolunteer() {
        return volunteer;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public String getAbout() {
        return about;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public String getWhatCanIHelp() {
        return whatCanIHelp;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDonated(double donated) {
        this.donated = donated;
    }

    public void setHeartInJar(int heartInJar) {
        this.heartInJar = heartInJar;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public void setStarInDarkSky(int starInDarkSky) {
        this.starInDarkSky = starInDarkSky;
    }

    public void setVolunteer(int volunteer) {
        this.volunteer = volunteer;
    }

    public void setWhatCanIHelp(String whatCanIHelp) {
        this.whatCanIHelp = whatCanIHelp;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAccessToken() {
        if (authenticationInfo != null)
            return authenticationInfo.getAccessToken();
        return null;
    }

    @Override
    public void setAccessToken(String accessToken) {
        if (authenticationInfo != null)
            authenticationInfo.setAccessToken(accessToken);
    }

    public AuthenticationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }
}
