package io.github.vikindor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    public String error;

    @JsonProperty("error_code")
    public int errorCode;

    @JsonProperty("error_tag")
    public String errorTag;

    @JsonProperty("http_code")
    public int httpCode;
}
