package com.ramzanlabs.shortener.awesomeshortener.link;

public class InvalidLinkPostRequestException extends Exception {
    public InvalidLinkPostRequestException(String message) {
        super(message);
    }
}
