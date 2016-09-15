package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private int photo_count;

    public int getPhotoCount() { return this.photo_count; }

    public void setPhotoCount(int photo_count) { this.photo_count = photo_count; }

    private Links2 links;

    public Links2 getLinks() { return this.links; }

    public void setLinks(Links2 links) { this.links = links; }
}
