package com.netegreek.chattr.responses;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookUserResponse {

    private Optional<String> email;

    @NotNull
    private String name;

    @NotNull
    private Long id;

    private Optional<FacebookDataResponse<FacebookPictureResponse>> picture;

    @JsonCreator
    public FacebookUserResponse(
            @JsonProperty("email") Optional<String> email,
            @JsonProperty("name") String name,
            @JsonProperty("id") Long id,
            @JsonProperty("picture") Optional<FacebookDataResponse<FacebookPictureResponse>> picture) {
        this.email = email;
        this.name = name;
        this.id = id;
        this.picture = picture;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getName() {
        return Optional.of(name);
    }

    public Long getId() {
        return id;
    }

    public Optional<FacebookDataResponse<FacebookPictureResponse>> getPicture() {
        return picture;
    }
}