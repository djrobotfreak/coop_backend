package com.netegreek.chattr.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/5/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookPictureResponse {

	private String url;

	private boolean isSilhouette;

    @JsonCreator
    public FacebookPictureResponse(
            @JsonProperty("url") String url,
            @JsonProperty("is_silhouette") boolean isSilhouette) {
        this.url = url;
        this.isSilhouette = isSilhouette;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSilhouette() {
        return isSilhouette;
    }
}
