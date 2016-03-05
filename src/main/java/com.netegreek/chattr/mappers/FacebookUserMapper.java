package com.netegreek.chattr.mappers;

import javax.inject.Inject;
import com.netegreek.chattr.db.User;
import com.netegreek.chattr.responses.FacebookUserResponse;

/**
 * Created by dwene on 3/5/16.
 */
public class FacebookUserMapper implements ResponseMapper<User, FacebookUserResponse> {

	@Inject
	public FacebookUserMapper(){
	}

	@Override
	public User createValueTFromResponse(FacebookUserResponse facebookUserResponse) {
		User user = new User();
		user.setEmail(facebookUserResponse.getEmail());
		user.setFBId(facebookUserResponse.getId());
		user.setName(facebookUserResponse.getName());
		return user;
	}
}
