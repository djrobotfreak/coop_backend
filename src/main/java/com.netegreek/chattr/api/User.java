package com.netegreek.chattr.api;

import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netegreek.chattr.models.db.Entity;
import com.netegreek.chattr.models.db.UserEntity;

public class User extends Savable implements Identifiable {

	private Long id;

	private String email;

	private String name;

	private String username;

	private Optional<String> photoUrl = Optional.empty();

	private String phone;

	@JsonIgnore
	private UserCredentials userCredentials;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Optional<String> getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = Optional.of(photoUrl);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Override
	public void updateEntity(Entity entity) {
		super.updateEntity(entity);
		UserEntity userEntity = (UserEntity) entity;
		userEntity.setEmail(email);
		userEntity.setName(name);
		userEntity.setUsername(username);
		if(photoUrl.isPresent()){
			userEntity.setPhotoUrl(photoUrl.get());
		}
		userEntity.setPhone(phone);
		userCredentials.updateEntity(userEntity);
	}

	@Override
	public void updateFromEntity(Entity entity) {

		super.updateFromEntity(entity);
		UserEntity userEntity = (UserEntity) entity;
		this.setId(entity.getId());
		this.setEmail(userEntity.getEmail());
		this.setName(userEntity.getName());
		this.setUsername(userEntity.getUsername());
		if (userEntity.getPhotoUrl() != null) {
			this.setPhotoUrl(userEntity.getPhotoUrl());
		}

		this.setPhone(userEntity.getPhone());

		this.userCredentials = new UserCredentials();
		this.userCredentials.updateFromEntity(userEntity);
	}
}
