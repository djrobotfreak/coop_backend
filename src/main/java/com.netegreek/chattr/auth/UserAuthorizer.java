package com.netegreek.chattr.auth;

import com.netegreek.chattr.api.BasicUser;
import io.dropwizard.auth.Authorizer;

public class UserAuthorizer implements Authorizer<BasicUser> {

    @Override
    public boolean authorize(BasicUser user, String role) {
        return true;
    }
}
