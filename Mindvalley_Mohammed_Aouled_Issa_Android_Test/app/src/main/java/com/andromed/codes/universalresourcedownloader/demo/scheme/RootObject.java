package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootObject
{
    private String id;

    @JsonProperty("id")
    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private Date created_at;

    @JsonProperty("created_at")
    public Date getCreatedAt() { return this.created_at; }

    public void setCreatedAt(Date created_at) { this.created_at = created_at; }

    private int width;

    @JsonProperty("width")
    public int getWidth() { return this.width; }

    public void setWidth(int width) { this.width = width; }

    private int height;

    @JsonProperty("height")
    public int getHeight() { return this.height; }

    public void setHeight(int height) { this.height = height; }

    private String color;

    @JsonProperty("color")
    public String getColor() { return this.color; }

    public void setColor(String color) { this.color = color; }

    private int likes;

    @JsonProperty("likes")
    public int getLikes() { return this.likes; }

    public void setLikes(int likes) { this.likes = likes; }

    private boolean liked_by_user;

    @JsonProperty("liked_by_user")
    public boolean getLikedByUser() { return this.liked_by_user; }

    public void setLikedByUser(boolean liked_by_user) { this.liked_by_user = liked_by_user; }

    private User user;

    @JsonProperty("user")
    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

    private ArrayList<Object> current_user_collections;

    @JsonProperty("current_user_collections")
    public ArrayList<Object> getCurrentUserCollections() { return this.current_user_collections; }

    public void setCurrentUserCollections(ArrayList<Object> current_user_collections) { this.current_user_collections = current_user_collections; }

    private Urls urls;

    @JsonProperty("urls")
    public Urls getUrls() { return this.urls; }

    public void setUrls(Urls urls) { this.urls = urls; }

    private ArrayList<Category> categories;

    @JsonProperty("categories")
    public ArrayList<Category> getCategories() { return this.categories; }

    public void setCategories(ArrayList<Category> categories) { this.categories = categories; }

    private Links3 links;

    @JsonProperty("links")
    public Links3 getLinks() { return this.links; }

    public void setLinks(Links3 links) { this.links = links; }
}
