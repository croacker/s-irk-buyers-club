package com.croacker.buyersclub.service.ofd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfdCheck {
    User                 string `json:"user"`
    RetailPlaceAddress   string `json:"retailPlaceAddress"`
    UserInn              string `json:"userInn"`
    RequestNumber        int    `json:"requestNumber"`
    ShiftNumber          int    `json:"shiftNumber"`
    Operator             string `json:"operator"`
    OperationType        int    `json:"operationType"`
    TotalSum             int    `json:"totalSum"`
    CashTotalSum         int    `json:"cashTotalSum"`
    EcashTotalSum        int    `json:"ecashTotalSum"`
    KktRegID             string `json:"kktRegId"`
    FiscalDriveNumber    string `json:"fiscalDriveNumber"`
    FiscalDocumentNumber int    `json:"fiscalDocumentNumber"`
    FiscalSign           int64  `json:"fiscalSign"`
    Nds10                int    `json:"nds10"`
    Nds18                int    `json:"nds18"`
    DateTime             int    `json:"dateTime"`
    TaxationType         int    `json:"taxationType"`
    Items                []struct {

    } `json:"items"`
    Discount        interface{} `json:"discount"`
    DiscountSum     interface{} `json:"discountSum"`
    KktNumber       interface{} `json:"kktNumber"`
    Markup          interface{} `json:"markup"`
    MarkupSum       interface{} `json:"markupSum"`
    Modifiers       interface{} `json:"modifiers"`
    Nds0            interface{} `json:"nds0"`
    NdsCalculated10 interface{} `json:"ndsCalculated10"`
    NdsCalculated18 interface{} `json:"ndsCalculated18"`
    NdsNo           interface{} `json:"ndsNo"`
    StornoItems     interface{} `json:"stornoItems"`
}
