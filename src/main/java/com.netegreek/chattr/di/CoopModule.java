package com.netegreek.chattr.di;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import com.netegreek.chattr.CoopConfiguration;
import dagger.Module;
import dagger.Provides;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

/**
 * Created by dwene on 3/1/16.
 */

@Module
public class CoopModule {

    private CoopConfiguration configuration;

    private Environment environment;

    private Client client;

    public CoopModule(CoopConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
        this.client = new JerseyClientBuilder(environment).using(environment).build("Facebook Client");
    }

    @Provides
    @Singleton
    public CoopConfiguration getConfiguration() {
        return configuration;
    }

    @Provides
    @Singleton
    public Environment getEnvironment() {
        return environment;
    }

    @Provides
    @Singleton
    public Client getClient() {
        return client;
    }
}
