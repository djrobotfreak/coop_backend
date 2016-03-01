package com.netegreek.chattr;

import com.netegreek.chattr.health.MarcoHealthCheck;
import com.netegreek.chattr.repositories.UserRepository;
import com.netegreek.chattr.resources.AuthenticationResource;
import com.netegreek.chattr.resources.HelloWorldResource;
import com.netegreek.chattr.resources.TextMessageResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.client.Client;

public class ChattrApplication extends Application<ChattrConfiguration> {
    public static void main(String[] args) throws Exception {
        new ChattrApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ChattrConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new AbstractBinder());
    }

    @Override
    public void run(ChattrConfiguration configuration, Environment environment) {

        environment.healthChecks().register("ping", new MarcoHealthCheck());
        environment.jersey().register(new HelloWorldResource());
		environment.jersey().register(new TextMessageResource());
        final Client client = new JerseyClientBuilder(environment)
                .using(configuration.getJerseyClientConfiguration())
                .build("JerseyClient");

        environment.jersey().register(client);
        environment.jersey().register(new AuthenticationResource(new UserRepository()));
    }
}
