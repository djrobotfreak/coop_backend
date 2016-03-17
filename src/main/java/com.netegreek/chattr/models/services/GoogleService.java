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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dwene on 3/15/16.
 */
public class GoogleService {

	private static final String CLIENT_ID = "460438236970-uecf0olbtirtp55r10a4cuev2ntiuep7.apps.googleusercontent.com";
	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleService.class);

	private NetHttpTransport httpTransport = new NetHttpTransport();
	private JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	@Inject
	public GoogleService() {
	}

	public boolean checkCredentials(String id, String token) {
		try {
			String tokenId = getIdFromToken(token);
			return tokenId.equals(id);
		} catch (BadJWTException ex ) {
			return false;
		}
	}

	public String getIdFromToken(String token) throws BadJWTException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
				httpTransport,
				jsonFactory)
				.setAudience(Arrays.asList(CLIENT_ID))
				.build();

		GoogleIdToken idToken;

		try {
			idToken = verifier.verify(token);
		} catch (GeneralSecurityException|IOException ex ){
			LOGGER.error(ex.getMessage());
			throw new BadJWTException();
		}

		if (idToken != null) {

			GoogleIdToken.Payload payload = idToken.getPayload();
			String userId = payload.getSubject();

			return userId;
		} else {
			LOGGER.error("The idToken was null");
			throw new BadJWTException();
		}
	}
}
