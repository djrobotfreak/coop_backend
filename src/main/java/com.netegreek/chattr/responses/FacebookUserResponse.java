package com.netegreek.chattr.responses;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class FacebookUserResponse {


    private String email;

    @NotNull
    private String name;

    @NotNull
    private Long id;

	@JsonUnwrapped()
	@JsonProperty("picture")
	private FacebookPictureResponse pictureResponse;

	public FacebookPictureResponse getPictureResponse() {
		return pictureResponse;
	}

	public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}