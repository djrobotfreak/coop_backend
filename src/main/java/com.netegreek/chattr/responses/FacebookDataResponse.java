package com.netegreek.chattr.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Derek on 3/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookDataResponse<T> {

    private T data;

    @JsonCreator
    public FacebookDataResponse(@JsonProperty("data") T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
