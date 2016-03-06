package com.netegreek.chattr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.netegreek.chattr.di.CoopComponent;
import com.netegreek.chattr.di.CoopModule;
import com.netegreek.chattr.di.DaggerCoopComponent;
import com.netegreek.chattr.health.MarcoHealthCheck;
import com.netegreek.chattr.resources.TextMessageResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CoopApplication extends Application<CoopConfiguration> {
    public static void main(String[] args) throws Exception {
        new CoopApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CoopConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(CoopConfiguration configuration, Environment environment) {
        CoopComponent component = DaggerCoopComponent.builder()
                .coopModule(new CoopModule(configuration, environment))
                .build();

        environment.jersey().register(component.authenticationResource());
        environment.jersey().register(new TextMessageResource());

        environment.healthChecks().register("ping", new MarcoHealthCheck());

        environment.getObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        environment.getObjectMapper().registerModule(new Jdk8Module());
    }
}
