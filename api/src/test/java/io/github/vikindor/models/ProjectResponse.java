package io.github.vikindor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponse {
    public String id;
    public String name;
}
