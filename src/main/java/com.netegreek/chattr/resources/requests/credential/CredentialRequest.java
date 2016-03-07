package com.netegreek.chattr.resources.requests.credential;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
		use=JsonTypeInfo.Id.NAME,
		include=JsonTypeInfo.As.PROPERTY,
		property="type",
		visible=false
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = FacebookCredentialRequest.class, name = "facebook"),
		@JsonSubTypes.Type(value = GoogleCredentialRequest.class, name = "google"),
		@JsonSubTypes.Type(value = PasswordCredentialRequest.class, name = "password")
})

public abstract class CredentialRequest {
}
