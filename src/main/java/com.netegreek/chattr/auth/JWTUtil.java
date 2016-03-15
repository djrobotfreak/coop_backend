package com.netegreek.chattr.auth;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import com.netegreek.chattr.CoopConfiguration;
import com.netegreek.chattr.api.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTUtil {

	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	private String secret;

	@Inject
	public JWTUtil(CoopConfiguration configuration) {
		this.secret = configuration.getAuthenticationConfiguration().getSecret();
	}

	public String createJWT(User user) {

		Date now = new Date();
		Date expires = Date.from(LocalDateTime.now().plusMonths(6).atZone(ZoneId.systemDefault()).toInstant());

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

		return Jwts.builder()
				.setId(user.getId().toString())
				.setExpiration(expires)
				.setIssuedAt(now)
				.setPayload("howdy")
				.signWith(SIGNATURE_ALGORITHM, signingKey)
				.compact();
	}

	public Long getIdByJWT(String jwt) throws ExpiredJwtException, BadJWTException {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret))
					.parseClaimsJws(jwt).getBody();
		} catch (IllegalArgumentException|MalformedJwtException|UnsupportedJwtException|SignatureException ex) {
			throw new BadJWTException();
		}
		return Long.parseLong(claims.getId());
	}
}
