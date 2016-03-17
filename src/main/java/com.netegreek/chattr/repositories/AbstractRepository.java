package com.netegreek.chattr.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import com.netegreek.chattr.models.db.Entity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public abstract class AbstractRepository {

	protected SessionFactory sessionFactory;

	public AbstractRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public <T extends Entity> T create(T instance) {
		sessionFactory.getCurrentSession().save(instance);
		return instance;
	}

	public <T extends Entity> void save(T instance) {
		sessionFactory.getCurrentSession().save(instance);
	}

	public <T extends Entity> List<T> loadByParameter(Class<T> clazz, String columnName, Serializable columnValue) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria = criteria.add(Restrictions.eq(columnName, columnValue));

		return criteria.list();
	}

	public <T extends Entity> T getById(Class<T> clazz, Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public <T extends Entity> Optional<T> getOneByParameter(Class<T> clazz, String columnName,
															Serializable columnValue) {
		List<T> list = loadByParameter(clazz, columnName, columnValue);
		if (list.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(list.get(0));
	}
}
