package com.netegreek.chattr.auth;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netegreek.chattr.CoopConfiguration;
import com.netegreek.chattr.api.BasicUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	private String secret;

	@Inject
	public JWTUtil(CoopConfiguration configuration) {
		this.secret = configuration.getAuthenticationConfiguration().getSecret();
	}

	public String createJWT(BasicUser user) {

		Date now = new Date();
		Date expires = Date.from(LocalDateTime.now().plusMonths(6).atZone(ZoneId.systemDefault()).toInstant());

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
		String basicUserString;
		try {
			 basicUserString = new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException ex) {
			throw new IllegalArgumentException("BasicUser failed to map to a string");
		}

		return Jwts.builder()
				.setId(basicUserString)
				.setExpiration(expires)
				.setIssuedAt(now)
				.signWith(SIGNATURE_ALGORITHM, signingKey)
				.compact();
	}

	public BasicUser getIdByJWT(String jwt) throws ExpiredJwtException, BadJWTException {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret))
					.parseClaimsJws(jwt).getBody();
		} catch (IllegalArgumentException|MalformedJwtException|UnsupportedJwtException|SignatureException ex) {
			throw new BadJWTException();
		}

		BasicUser basicUser;

		try {
			basicUser = new ObjectMapper().readValue(claims.getId(), BasicUser.class);
		} catch (IOException ex) {
			LOGGER.error("couldnt parse id string");
			throw new BadJWTException();
		}
		return basicUser;
	}
}
