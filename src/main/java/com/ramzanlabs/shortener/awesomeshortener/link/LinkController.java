package com.ramzanlabs.shortener.awesomeshortener.link;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/link")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> postLink(@RequestBody LinkPostRequest linkPostRequest) {
        try {
            return ResponseEntity.ok(
                    linkService.addLink(linkPostRequest)
            );
        } catch (InvalidLinkPostRequestException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @GetMapping("/get/{token}")
    public ResponseEntity<?> getLink(@PathVariable @NonNull String token) {
        try {
            return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                    .header("Location",
                            linkService.getRedirectForToken(token))
                    .build();
        } catch (NonFoundLinkTokenException exception) {
            return ResponseEntity.notFound().build();
        }
    }


}
