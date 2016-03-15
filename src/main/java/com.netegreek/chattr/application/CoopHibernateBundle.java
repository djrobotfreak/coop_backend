package com.netegreek.chattr.application;

import com.google.common.collect.ImmutableList;
import com.netegreek.chattr.CoopConfiguration;
import com.netegreek.chattr.models.db.UserEntity;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;


public class CoopHibernateBundle extends HibernateBundle<CoopConfiguration> {
	private final static ImmutableList<Class<?>> entityClasses =
			ImmutableList.of(
					UserEntity.class);

	public CoopHibernateBundle() {
		super(entityClasses, new SessionFactoryFactory());
	}

	@Override
	public PooledDataSourceFactory getDataSourceFactory(CoopConfiguration configuration) {
		return configuration.getDataSourceFactory();
	}
}
