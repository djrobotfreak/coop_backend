package com.netegreek.chattr;

import com.netegreek.chattr.health.MarcoHealthCheck;
import com.netegreek.chattr.resources.HelloWorldResource;
import com.netegreek.chattr.resources.TextMessageResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ChattrApplication extends Application<ChattrConfiguration> {
    public static void main(String[] args) throws Exception {
        new ChattrApplication().run(args);
    }

    @Override
    public String getName() {
        return "howdy";
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
    }

    @Override
    public void run(ChattrConfiguration configuration, Environment environment) {

        environment.healthChecks().register("ping", new MarcoHealthCheck());
        environment.jersey().register(new HelloWorldResource());
		environment.jersey().register(new TextMessageResource());
    }
}
