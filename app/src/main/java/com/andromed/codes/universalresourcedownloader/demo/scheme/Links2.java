package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links2
{
    private String self;

    public String getSelf() { return this.self; }

    public void setSelf(String self) { this.self = self; }

    private String photos;

    public String getPhotos() { return this.photos; }

    public void setPhotos(String photos) { this.photos = photos; }
}
