package com.netegreek.chattr.auth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Before;
import org.junit.Test;
import com.netegreek.chattr.CoopConfiguration;
import com.netegreek.chattr.api.BasicUser;
import com.netegreek.chattr.application.config.AuthenticationConfiguration;


public class JWTUtilTest {

	private JWTUtil jwtUtil;

	@Before
	public void setup() {
		CoopConfiguration coopConfiguration = new CoopConfiguration();
		String secret = "YWI1MTg1YTctNGJjMi00YzcyLTkyOWQtNDZjNjJiZGRiZDE1";
		AuthenticationConfiguration authenticationConfiguration = new AuthenticationConfiguration(secret);
		coopConfiguration.setAuthenticationConfiguration(authenticationConfiguration);
		jwtUtil = new JWTUtil(coopConfiguration);
	}

	@Test
	public void CreateJWT_GetJWT() {
		BasicUser user = new BasicUser();
		user.setId(123L);
		user.setName("Derek J Wene");
		user.setUsername("djrobotfreak");
		String token = jwtUtil.createJWT(user);
		try {
			BasicUser returnedUser = jwtUtil.getIdByJWT(token);
			assertThat(returnedUser.getName(), is(equalTo(user.getName())));
		} catch (Exception ex) {
		}

	}

}