package com.netegreek.chattr.mappers;

/**
 * Created by dwene on 3/5/16.
 */
public interface ResponseMapper<ValueT, ResponseT> {

	public ValueT createValueTFromResponse(ResponseT responseT);

}
