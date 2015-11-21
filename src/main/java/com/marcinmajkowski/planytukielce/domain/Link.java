package com.marcinmajkowski.planytukielce.domain;

/**
 * Created by Marcin on 19.11.2015.
 */
public class Link {
    private String name;
    private String url;

    public Link() {
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
