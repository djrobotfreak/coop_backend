package com.netegreek.chattr.auth;

import java.math.BigInteger;
import java.security.SecureRandom;
import com.lambdaworks.crypto.SCryptUtil;

/**
 * Created by dwene on 3/6/16.
 */
public class BasicCredentials {

	public static boolean checkPassword(String password, String stored) {
		return SCryptUtil.check(password, stored);
	}

	public static String hashPassword(String password) {
		return SCryptUtil.scrypt(password, 20, 21, 15);
	}

	public static String generateToken() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}
}
