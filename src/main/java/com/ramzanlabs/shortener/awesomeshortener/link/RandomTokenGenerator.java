package com.ramzanlabs.shortener.awesomeshortener.link;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RandomTokenGenerator {
    private static final int DEFAULT_TOKEN_SIZE = 8;
    private final LinkRepository linkRepository;

    @Autowired
    public RandomTokenGenerator(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String generateRandomToken() {
        int size = DEFAULT_TOKEN_SIZE;
        String token = generateRandomString(size);

        while (!tokenIsUnique(token)) {
            token = generateRandomString(++size);
        }
        return token;

    }

    private String generateRandomString(int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }

    private boolean tokenIsUnique(String token) {
        Optional<Link> similar = linkRepository.findAll()
                .stream()
                .filter(link -> link.getToken().equals(token))
                .findAny();
        return similar.isEmpty();
    }
}
