package com.netegreek.chattr.responses;

import javax.validation.constraints.NotNull;

public class FacebookUserResponse {


    private String email;

    @NotNull
    private String name;

    @NotNull
    private Long id;

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