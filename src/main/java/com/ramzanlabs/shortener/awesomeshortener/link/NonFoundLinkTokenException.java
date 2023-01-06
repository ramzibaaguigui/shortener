package com.ramzanlabs.shortener.awesomeshortener.link;

public class NonFoundLinkTokenException extends Exception {
    public NonFoundLinkTokenException(String message) {
        super(message);
    }
}
