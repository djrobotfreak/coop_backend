package com.netegreek.chattr;


import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netegreek.chattr.application.config.AuthenticationConfiguration;
import com.netegreek.chattr.application.config.FacebookConfiguration;
import com.netegreek.chattr.application.config.GoogleConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CoopConfiguration extends Configuration {

	@NotNull
	@JsonProperty("database")
    private DataSourceFactory dataSourceFactory;

	@NotNull
	@JsonProperty("auth")
	private AuthenticationConfiguration authenticationConfiguration;

	private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

	@NotNull
	@JsonProperty("google")
	private GoogleConfiguration googleConfiguration;

	@NotNull
	@JsonProperty("facebook")
	private FacebookConfiguration facebookConfiguration;


	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}


	public AuthenticationConfiguration getAuthenticationConfiguration() {
		return authenticationConfiguration;
	}

	public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
	}

	public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClientConfiguration) {
        this.jerseyClientConfiguration = jerseyClientConfiguration;
    }

	public GoogleConfiguration getGoogleConfiguration() {
		return googleConfiguration;
	}

	public void setGoogleConfiguration(GoogleConfiguration googleConfiguration) {
		this.googleConfiguration = googleConfiguration;
	}

	public FacebookConfiguration getFacebookConfiguration() {
		return facebookConfiguration;
	}

	public void setFacebookConfiguration(FacebookConfiguration facebookConfiguration) {
		this.facebookConfiguration = facebookConfiguration;
	}
}
