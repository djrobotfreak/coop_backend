package com.netegreek.chattr.db;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Derek on 2/29/16.
 */
public class User {

    private UUID id;

    private Long FBId;

    private String email;

    private String name;

    private Collection<String> tokens;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getFBId() {
        return FBId;
    }

    public void setFBId(Long FBId) {
        this.FBId = FBId;
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
}
