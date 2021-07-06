package com.croacker.buyersclub.service.ofd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private int price;

    @JsonProperty("quantity")
    private float quantity;

    @JsonProperty("sum")
    private int sum;

    @JsonProperty("storno")
    private boolean storno;

    @JsonProperty("nds0")
    private int nds0;

    @JsonProperty("nds10")
    private int nds10;

    @JsonProperty("nds18")
    private int nds18;

    @JsonProperty("ndsCalculated10")
    private String ndsCalculated10;

    @JsonProperty("ndsCalculated18")
    private String ndsCalculated18;

    @JsonProperty("ndsNo")
    private String NdsNo;
}
