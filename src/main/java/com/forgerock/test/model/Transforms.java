package com.forgerock.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;

@Getter
public class Transforms {
    @JsonProperty("name")
    private String name;
    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("useInML")
    private boolean useInML;
    @JsonProperty("jsltExpression")
    @JsonRawValue
    private String jsltExpression;
}