package com.croacker.buyersclub.service.ofd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("name")
    private String name            string      `json:"name"`
    @JsonProperty("name")
    private int Price           int         `json:"price"`
    private float Quantity        float64     `json:"quantity"`
    private int Sum             int         `json:"sum"`
    private boolean Storno          bool        `json:"storno"`
    private int Nds0            int         `json:"nds0"`
    private int Nds10           int         `json:"nds10"`
    private int Nds18           int         `json:"nds18"`
    private String Modifiers       interface{} `json:"modifiers"`
    private String NdsCalculated10 interface{} `json:"ndsCalculated10"`
    private String NdsCalculated18 interface{} `json:"ndsCalculated18"`
    private String NdsNo           interface{} `json:"ndsNo"`
}
