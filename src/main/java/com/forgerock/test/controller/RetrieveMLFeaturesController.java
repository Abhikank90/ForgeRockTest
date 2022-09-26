package com.forgerock.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.forgerock.test.service.RetrieveMLFeaturesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveMLFeaturesController {

    private RetrieveMLFeaturesService retrieveMLFeaturesService;

    public RetrieveMLFeaturesController(RetrieveMLFeaturesService retrieveMLFeaturesService) {
        this.retrieveMLFeaturesService = retrieveMLFeaturesService;
    }

    @PostMapping(value="/retrieve", consumes={"application/json"}, produces={"application/json"})
    public @ResponseBody String testBean(@RequestBody String request) throws JsonProcessingException {
        return retrieveMLFeaturesService.retrieveFeatures(request);
    }
}
