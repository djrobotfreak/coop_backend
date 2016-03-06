package com.netegreek.chattr.di;

import com.netegreek.chattr.clients.FacebookClient;
import com.netegreek.chattr.resources.AuthenticationResource;
import dagger.Component;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;

/**
 * Created by dwene on 3/1/16.
 */

@Singleton
@Component(modules = CoopModule.class)
public interface CoopComponent {

    Client client();

    FacebookClient facebookTokenService();

    AuthenticationResource authenticationResource();
}
