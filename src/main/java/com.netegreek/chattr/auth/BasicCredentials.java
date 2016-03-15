package com.netegreek.chattr.auth;

import com.lambdaworks.crypto.SCryptUtil;

public class BasicCredentials {

	public static boolean checkPassword(String password, String stored) {
		return SCryptUtil.check(password, stored);
	}

	public static String hashPassword(String password) {
		return SCryptUtil.scrypt(password, 20, 21, 15);
	}
}
