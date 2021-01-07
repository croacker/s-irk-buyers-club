package com.croacker.buyersclub.service.ofd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfdCheck {
    @JsonProperty("user")
    private String user;

    @JsonProperty("retailPlaceAddress")
    private String retailPlaceAddress;

    @JsonProperty("userInn")
    private String userInn;

    @JsonProperty("requestNumber")
    private String requestNumber;

    @JsonProperty("shiftNumber")
    private String shiftNumber;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("operationType")
    private int operationType;

    @JsonProperty("totalSum")
    private int totalSum;

    @JsonProperty("cashTotalSum")
    private int cashTotalSum;

    @JsonProperty("ecashTotalSum")
    private int ecashTotalSum;

    @JsonProperty("kktRegId")
    private String kktRegId;

    @JsonProperty("fiscalDriveNumber")
    private String fiscalDriveNumber;

    @JsonProperty("fiscalDocumentNumber")
    private String fiscalDocumentNumber;

    @JsonProperty("fiscalSign")
    private String fiscalSign;

    @JsonProperty("nds10")
    private int nds10;

    @JsonProperty("nds18")
    private int nds18;

    @JsonProperty("dateTime")
    private int dateTime;

    @JsonProperty("taxationType")
    private int taxationType;

    @JsonProperty("items")
    private List<Item> Items;

    @JsonProperty("discount")
    private String discount;

    @JsonProperty("discountSum")
    private String discountSum;

    @JsonProperty("kktNumber")
    private String kktNumber;

    @JsonProperty("markup")
    private String markup;

    @JsonProperty("markupSum")
    private String markupSum;

    @JsonProperty("nds0")
    private String nds0;

    @JsonProperty("ndsCalculated10")
    private String ndsCalculated10;

    @JsonProperty("ndsCalculated18")
    private String ndsCalculated18;

    @JsonProperty("ndsNo")
    private String ndsNo;
}
