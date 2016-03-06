package com.netegreek.chattr.db;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Derek on 2/29/16.
 */
public class User {

    private UUID id;

    private Long FacebookId;

    private String email;

    private String name;

    private Collection<String> tokens;

    private String photoUrl;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getFacebookId() {
        return FacebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.FacebookId = facebookId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<String> tokens) {
        this.tokens = tokens;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
