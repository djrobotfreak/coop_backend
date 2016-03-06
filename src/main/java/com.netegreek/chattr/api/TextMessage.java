package com.netegreek.chattr.api;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class TextMessage {

    private String body;

    private long to;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextMessage that = (TextMessage) o;
        return to == that.to &&
                Objects.equal(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(body, to);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("body", body)
                .add("to", to)
                .toString();
    }
}