package com.netegreek.chattr.di;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import com.netegreek.chattr.CoopConfiguration;
import com.netegreek.chattr.application.CoopHibernateBundle;
import dagger.Module;
import dagger.Provides;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

@Module
public class CoopModule {

    private CoopConfiguration configuration;

    private Environment environment;

	private CoopHibernateBundle hibernateBundle;

    private Client client;

    public CoopModule(CoopConfiguration configuration, Environment environment, CoopHibernateBundle hibernateBundle) {
        this.configuration = configuration;
        this.environment = environment;
		this.hibernateBundle = hibernateBundle;
        this.client = new JerseyClientBuilder(environment).using(environment).build("Facebook Client");
    }

	@Provides
	public SessionFactory getSessionFactory() {
		return hibernateBundle.getSessionFactory();
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
