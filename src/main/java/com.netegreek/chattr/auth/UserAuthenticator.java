package com.netegreek.chattr.auth;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.netegreek.chattr.api.BasicUser;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class UserAuthenticator implements Authenticator<BasicUser, BasicUser> {
    /**
     * Valid users with mapping user -> roles
     */


	@Override
	public com.google.common.base.Optional<BasicUser> authenticate(BasicUser credentials) throws AuthenticationException {
		return com.google.common.base.Optional.of(credentials);
	}
}
