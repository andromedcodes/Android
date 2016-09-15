package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileImage
{
    private String small;

    public String getSmall() { return this.small; }

    public void setSmall(String small) { this.small = small; }

    private String medium;

    public String getMedium() { return this.medium; }

    public void setMedium(String medium) { this.medium = medium; }

    private String large;

    public String getLarge() { return this.large; }

    public void setLarge(String large) { this.large = large; }
}
