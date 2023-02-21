package com.cellulant.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill {

    private String billID;
    private String billingServiceID;
    private String currencyCode;
    private String accountNumber;
    private String msisdn;
    private Double dueAmount;

}
