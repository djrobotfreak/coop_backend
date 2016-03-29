package com.netegreek.chattr.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lambdaworks.crypto.SCryptUtil;

public class BasicCredentials {
	private final static Logger LOGGER = LoggerFactory.getLogger(BasicCredentials.class);
	public static boolean checkPassword(String password, String stored) {
		LOGGER.info("Stored Password: " + stored);
		return SCryptUtil.check(password, stored);
	}

	public static String hashPassword(String password) {
		String hashed_password = SCryptUtil.scrypt(password, 16, 8, 8);
		LOGGER.info("Hashed Password: " + hashed_password);
		return hashed_password;
	}
}
