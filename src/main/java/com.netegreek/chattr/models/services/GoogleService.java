package com.netegreek.chattr.models.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.inject.Inject;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.netegreek.chattr.auth.BadJWTException;

/**
 * Created by dwene on 3/15/16.
 */
public class GoogleService {

	private static final String CLIENT_ID = "460438236970-uecf0olbtirtp55r10a4cuev2ntiuep7.apps.googleusercontent.com";

	private NetHttpTransport httpTransport = new NetHttpTransport();
	private JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	@Inject
	public GoogleService() {
	}

	public boolean checkCredentials(Long id, String token) {
		try {
			Long tokenId = getIdFromToken(token);
			return tokenId.equals(id);
		} catch (BadJWTException ex ) {
			return false;
		}
	}

	public Long getIdFromToken(String token) throws BadJWTException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
				httpTransport,
				jsonFactory)
				.setAudience(Arrays.asList(CLIENT_ID))
				.setIssuer("https://accounts.google.com")
				.build();

		GoogleIdToken idToken;

		try {
			idToken = verifier.verify(token);
		} catch (GeneralSecurityException|IOException ex ){
			throw new BadJWTException();
		}

		if (idToken != null) {

			GoogleIdToken.Payload payload = idToken.getPayload();
			String userId = payload.getSubject();

			return Long.parseLong(userId);
		} else {
			throw new BadJWTException();
		}
	}
}
