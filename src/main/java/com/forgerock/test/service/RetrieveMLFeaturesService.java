package com.forgerock.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgerock.test.model.FeatureConfig;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RetrieveMLFeaturesService {

    /**
     * Method to retrive ML features
     * @param request
     * @return
     */
    public String retrieveFeatures(String request) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        JsonNode json = mapper.readTree(request);

        FeatureConfig featureConfig =mapper.convertValue(json.get("featureConfig"), FeatureConfig.class);
        JsonNode inputJson = json.get("inputJson");

        JSONObject returnObj = new JSONObject();
        returnObj.put("eventId", "test");
        if(!inputJson.get("eventId").isNull() ) {
            String eventId = inputJson.get("eventId").toString().replaceAll("\"", "");
            returnObj.put("eventId", eventId);
        }

        featureConfig.getTransforms().parallelStream().forEach(transform -> {
            if(transform.isEnabled()){
                String jstlStr = transform.getJsltExpression();
                Expression jstlExp = Parser.compileString(jstlStr);
                String outputStr = jstlExp.apply(inputJson).toString().replaceAll("\"", "");
                returnObj.put(transform.getName(), outputStr);
            }
        });
        return returnObj.toJSONString();
    }
}
