package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private String username;

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private ProfileImage profile_image;

    public ProfileImage getProfileImage() { return this.profile_image; }

    public void setProfileImage(ProfileImage profile_image) { this.profile_image = profile_image; }

    private Links links;

    public Links getLinks() { return this.links; }

    public void setLinks(Links links) { this.links = links; }
}
