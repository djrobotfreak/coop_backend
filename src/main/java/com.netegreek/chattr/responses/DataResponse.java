package com.netegreek.chattr.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dwene on 3/8/16.
 */
public class DataResponse<T> {

	private T data;

	@JsonCreator
	public DataResponse(@JsonProperty("data") T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

}
