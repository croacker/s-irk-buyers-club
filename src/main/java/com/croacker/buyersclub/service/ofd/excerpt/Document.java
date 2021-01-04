package com.croacker.buyersclub.service.ofd.excerpt;

import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
    @JsonProperty("receipt")
    private OfdCheck ofdCheck;
}
