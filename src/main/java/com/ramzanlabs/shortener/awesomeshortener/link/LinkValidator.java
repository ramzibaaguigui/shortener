package com.ramzanlabs.shortener.awesomeshortener.link;

import org.springframework.stereotype.Component;

@Component
public class LinkValidator {

    public boolean validateLink(LinkPostRequest linkPostRequest) {
        return linkPostRequest.getName() != null && linkPostRequest.getRedirect() != null;
    }
}
