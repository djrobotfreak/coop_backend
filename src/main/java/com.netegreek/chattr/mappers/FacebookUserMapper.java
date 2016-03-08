package com.netegreek.chattr.mappers;

import javax.inject.Inject;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.responses.FacebookUserResponse;

/**
 * Created by dwene on 3/5/16.
 */
public class FacebookUserMapper implements ResponseMapper<User, FacebookUserResponse> {

    @Inject
    public FacebookUserMapper() {
    }

    @Override
    public User createValueTFromResponse(FacebookUserResponse facebookUserResponse) {
        User user = new User();
        if (facebookUserResponse.getEmail().isPresent()) {
            user.setEmail(facebookUserResponse.getEmail().get());
        }
        if (facebookUserResponse.getPicture().isPresent()) {
            user.setPhotoUrl(facebookUserResponse.getPicture().get().getData().getUrl());
        }
        user.setName(facebookUserResponse.getName().get());
        return user;
    }

    public User updateValueTFromResponse(User user, FacebookUserResponse facebookUserResponse) {
        if (facebookUserResponse.getEmail().isPresent()) {
            user.setEmail(facebookUserResponse.getEmail().get());
        }
        if (facebookUserResponse.getPicture().isPresent()) {
            user.setPhotoUrl(facebookUserResponse.getPicture().get().getData().getUrl());
        }
        user.setName(facebookUserResponse.getName().get());
        return user;
    }
}