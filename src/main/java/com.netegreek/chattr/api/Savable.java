package com.netegreek.chattr.api;

import com.netegreek.chattr.models.db.Entity;

/**
 * Created by Derek on 3/14/16.
 */
public abstract class Savable implements Identifiable {

	private Long id;

	public void updateEntity(Entity entity) {
		entity.setId(id);
	}

	public void updateFromEntity(Entity entity) {
		this.setId(entity.getId());
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
