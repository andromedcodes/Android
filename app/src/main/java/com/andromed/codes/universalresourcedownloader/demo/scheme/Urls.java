package com.andromed.codes.universalresourcedownloader.demo.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Utilisateur on 10/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Urls
{

    private String raw;

    @JsonProperty("raw")
    public String getRaw() { return this.raw; }

    public void setRaw(String raw) { this.raw = raw; }

    @JsonProperty("full")
    private String full;

    public String getFull() { return this.full; }

    public void setFull(String full) { this.full = full; }

    private String regular;

    @JsonProperty("regular")
    public String getRegular() { return this.regular; }

    public void setRegular(String regular) { this.regular = regular; }

    private String small;

    @JsonProperty("small")
    public String getSmall() { return this.small; }

    public void setSmall(String small) { this.small = small; }

    private String thumb;

    @JsonProperty("thumb")
    public String getThumb() { return this.thumb; }

    public void setThumb(String thumb) { this.thumb = thumb; }
}