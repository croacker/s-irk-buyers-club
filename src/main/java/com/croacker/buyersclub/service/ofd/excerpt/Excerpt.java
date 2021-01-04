package com.croacker.buyersclub.service.ofd.excerpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Excerpt {
    @JsonProperty("claims")
    private List<Object> claims;
    @JsonProperty("ticket")
    private Ticket ticket;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("timeLastAccess")
    private String timeLastAccess;
}
