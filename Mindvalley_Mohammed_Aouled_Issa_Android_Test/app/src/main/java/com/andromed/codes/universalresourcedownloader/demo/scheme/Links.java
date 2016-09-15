package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links
{
    private String self;

    public String getSelf() { return this.self; }

    public void setSelf(String self) { this.self = self; }

    private String html;

    public String getHtml() { return this.html; }

    public void setHtml(String html) { this.html = html; }

    private String photos;

    public String getPhotos() { return this.photos; }

    public void setPhotos(String photos) { this.photos = photos; }

    private String likes;

    public String getLikes() { return this.likes; }

    public void setLikes(String likes) { this.likes = likes; }
}
