package com.netegreek.chattr;


import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

public class CoopConfiguration extends Configuration {

    private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClientConfiguration) {
        this.jerseyClientConfiguration = jerseyClientConfiguration;
    }
}
