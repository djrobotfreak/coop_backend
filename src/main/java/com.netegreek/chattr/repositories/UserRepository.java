package com.netegreek.chattr.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import com.netegreek.chattr.db.User;

/**
 * TODO: This needs to actually hook-up with the database.
 */
public class UserRepository {

    @Inject
    public UserRepository() {
    }

    private Map<UUID, User> userRepository = new HashMap<UUID, User>();

    public Optional<User> getById(UUID uuid) {
        if (this.userRepository.containsKey(uuid)) {
            return Optional.of(this.userRepository.get(uuid));
        } else {
            return Optional.empty();
        }
    }

	public Optional<User> getByUsername(String username) {
		for (Map.Entry<UUID, User> user : userRepository.entrySet()) {
			if (user.getValue().getUsername().equals(username)) {
				return Optional.of(user.getValue());
			}
		}
		return Optional.empty();
	}

	public Optional<User> getByPhone(String phone) {
		for (Map.Entry<UUID, User> user : userRepository.entrySet()) {
			if (user.getValue().getPhone().equals(phone)) {
				return Optional.of(user.getValue());
			}
		}
		return Optional.empty();
	}

    public Optional<User> getByFacebookId(Long id) {
		for (Map.Entry<UUID, User> user : userRepository.entrySet()) {
			if (user.getValue().getUserCredentials().getFacebookId().isPresent()) {
				if (user.getValue().getUserCredentials().getFacebookId().get().equals(id)) {
					return Optional.of(user.getValue());
				}
			}
		}
		return Optional.empty();
    }

	public Optional<User> getByGoogleId(Long id) {
		for (Map.Entry<UUID, User> user : userRepository.entrySet()) {
			if (user.getValue().getUserCredentials().getGoogleId().isPresent()) {
				if (user.getValue().getUserCredentials().getGoogleId().get().equals(id)) {
					return Optional.of(user.getValue());
				}
			}
		}
		return Optional.empty();
	}

    public void save(User user) {
        this.userRepository.put(user.getId(), user);
    }
}
