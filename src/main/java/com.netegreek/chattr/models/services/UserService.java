package com.netegreek.chattr.models.services;

import javax.inject.Inject;
import com.netegreek.chattr.repositories.UserRepository;

/**
 * Created by Derek on 3/7/16.
 */
public class UserService {

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameAvailable(String username) {
        return !this.userRepository.getByUsername(username.toLowerCase()).isPresent();
    }

    public boolean isPhoneAvailable(String phone) {
        return !this.userRepository.getByPhone(phone).isPresent();
    }

}
