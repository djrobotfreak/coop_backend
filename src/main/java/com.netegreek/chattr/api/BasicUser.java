package com.netegreek.chattr.api;

import java.security.Principal;
import com.google.common.base.MoreObjects;

public class BasicUser implements Principal {

	private Long id;

	private String name;

	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void updateFromUser(User user) {
		this.username = user.getUsername();
		this.id = user.getId();
		this.name = user.getName();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("username", username)
				.toString();
	}
}
