package com.croacker.buyersclub.service.ofd;

import com.croacker.buyersclub.service.ofd.excerpt.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfdRoot {
    @JsonProperty("_id")
    public String id;
    @JsonProperty("createdAt")
    public String createdAt;
    @JsonProperty("ticket")
    public Ticket ticket;
}
