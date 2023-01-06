package com.ramzanlabs.shortener.awesomeshortener.link;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPostRequest {
    private String name;
    private String redirect;

}
