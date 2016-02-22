package com.netegreek.chattr.health;

import com.codahale.metrics.health.HealthCheck;

public class MarcoHealthCheck extends HealthCheck {

    public MarcoHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Polo");
    }
}
