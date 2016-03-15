package com.netegreek.chattr.models.db;

/**
 * Created by Derek on 3/14/16.
 */
public abstract class Entity {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
