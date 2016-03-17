package com.netegreek.chattr;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netegreek.chattr.api.BasicUser;
import com.netegreek.chattr.application.CoopHibernateBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jetty.setup.ServletEnvironment;
import io.dropwizard.migrations.MigrationsBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.netegreek.chattr.auth.UserAuthFilter;
import com.netegreek.chattr.auth.UserAuthenticator;
import com.netegreek.chattr.auth.UserAuthorizer;
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
	private static final CoopHibernateBundle COOP_HIBERNATE_BUNDLE = new CoopHibernateBundle();

    @Override
    public void initialize(Bootstrap<CoopConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

		bootstrap.addBundle(new MigrationsBundle<CoopConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(CoopConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});

		bootstrap.addBundle(COOP_HIBERNATE_BUNDLE);
    }

    @Override
    public void run(CoopConfiguration configuration, Environment environment) {
        CoopComponent component = DaggerCoopComponent.builder()
                .coopModule(new CoopModule(configuration, environment, COOP_HIBERNATE_BUNDLE))
                .build();
		environment.jersey().register( new AuthDynamicFeature(new UserAuthFilter.Builder<BasicUser>(component.jwtUtil())
				.setAuthenticator(new UserAuthenticator())
				.setAuthorizer(new UserAuthorizer())
				.buildAuthFilter())
		);


		registerResources(environment, component);

        environment.healthChecks().register("ping", new MarcoHealthCheck());

        configureJackson(environment.getObjectMapper());
		configureServlets(environment.servlets());
	}

	private void registerResources(Environment environment, CoopComponent component) {
		environment.jersey().register(component.authenticationResource());
		environment.jersey().register(new TextMessageResource());
		environment.jersey().register(component.userResource());
	}

	private void configureJackson(ObjectMapper mapper) {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.registerModule(new Jdk8Module());
	}

	private void configureServlets(ServletEnvironment servlets) {
		FilterRegistration.Dynamic filter = servlets.addFilter("CORS", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),
				true, "/*");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
				"GET,PUT,POST,DELETE,OPTIONS");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(
				CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		filter.setInitParameter("allowedHeaders",
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
		filter.setInitParameter("allowCredentials", "true");
	}


}
