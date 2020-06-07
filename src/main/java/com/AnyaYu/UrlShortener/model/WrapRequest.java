package com.AnyaYu.UrlShortener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WrapRequest {

    private String urlString;

    public WrapRequest(String urlString) {
        this.urlString = urlString;
    }

    public WrapRequest() {
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(@JsonProperty("urlString") String urlString) {
        this.urlString = urlString;
    }

}
