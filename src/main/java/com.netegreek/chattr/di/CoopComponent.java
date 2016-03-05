package com.netegreek.chattr.di;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import com.netegreek.chattr.resources.AuthenticationResource;
import com.netegreek.chattr.clients.FacebookClient;
import dagger.Component;

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
