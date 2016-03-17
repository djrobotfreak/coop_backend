package com.netegreek.chattr.repositories;

import java.util.Optional;
import javax.inject.Inject;
import com.netegreek.chattr.models.db.UserEntity;
import org.hibernate.SessionFactory;

/**
 * TODO: This needs to actually hook-up with the database.
 */
public class UserRepository extends AbstractRepository {

	@Inject
	public UserRepository(SessionFactory sessionFactory){
		super(sessionFactory);
	}

    public Optional<UserEntity> getById(Long id) {
		UserEntity entity = super.getById(UserEntity.class, id);
		if (entity == null) {
			return Optional.empty();
		}
		return Optional.of(entity);
    }

	public Optional<UserEntity> getByUsername(String username) {

		return super.getOneByParameter(UserEntity.class, "username", username);
	}

	public Optional<UserEntity> getByPhone(String phone) {
		return super.getOneByParameter(UserEntity.class, "phone", phone);
	}

    public Optional<UserEntity> getByFacebookId(Long facebookId) {
		return super.getOneByParameter(UserEntity.class, "facebookId", facebookId);
    }

	public Optional<UserEntity> getByGoogleId(String googleId) {
		return super.getOneByParameter(UserEntity.class, "googleId", googleId);
	}
}
