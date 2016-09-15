package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links3
{
    private String self;

    public String getSelf() { return this.self; }

    public void setSelf(String self) { this.self = self; }

    private String html;

    public String getHtml() { return this.html; }

    public void setHtml(String html) { this.html = html; }

    private String download;

    public String getDownload() { return this.download; }

    public void setDownload(String download) { this.download = download; }
}