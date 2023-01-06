package com.ramzanlabs.shortener.awesomeshortener.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    private final LinkValidator linkValidator;
    private final RandomTokenGenerator tokenGenerator;
    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkValidator linkValidator,
                       RandomTokenGenerator tokenGenerator,
                       LinkRepository linkRepository) {
        this.tokenGenerator = tokenGenerator;
        this.linkValidator = linkValidator;
        this.linkRepository = linkRepository;
    }

    public Link addLink(LinkPostRequest linkPostRequest) throws InvalidLinkPostRequestException {
            boolean isValid = linkValidator.validateLink(linkPostRequest);
            if (!isValid) {
                throw new InvalidLinkPostRequestException("The link is not valid");
            }
            Link link = new Link();
            link.setName(linkPostRequest.getName());
            link.setRedirect(linkPostRequest.getRedirect());
            link.setToken(tokenGenerator.generateRandomToken());
            return linkRepository.save(link);
    }

    public String getRedirectForToken(String token) throws NonFoundLinkTokenException {
        return linkRepository.findAll()
                .stream()
                .filter(link -> link.getToken().equals(token))
                .findAny()
                .map(Link::getRedirect)
                .orElseThrow(() -> new NonFoundLinkTokenException(String.format("Cannot find link with token %s", token)));
    }
}
