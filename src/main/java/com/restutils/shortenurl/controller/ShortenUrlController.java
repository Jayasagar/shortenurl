package com.restutils.shortenurl.controller;

import com.restutils.shortenurl.model.URL;
import com.restutils.shortenurl.service.ShortenUrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ShortenUrlController {

    @Autowired
    private ShortenUrlService shortenUrlService;

    @RequestMapping(value = "/shortenurl", method = RequestMethod.POST)
    public ResponseEntity<String> generate(@RequestBody URL url) {
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(url.getUrl())) {
            String tinyUrl = shortenUrlService.generateUrl(url.getUrl());
            //TODO: hardcoded url
            return new ResponseEntity<>("http://localhost:8902/restutils/api/v1/" + tinyUrl, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void sendRedirect(@PathVariable String id, HttpServletResponse resp) throws Exception {
        String actualUrl = shortenUrlService.getUrl(id);
        if (actualUrl != null) {
            resp.sendRedirect(actualUrl);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        boolean deleted = shortenUrlService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
