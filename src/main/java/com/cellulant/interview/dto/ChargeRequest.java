package com.cellulant.interview.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeRequest {
    private String originatorMSISDN;
    private String payerClientCode;
    private String payerClientName;
    private String checkoutType;
    private String countryCode;
    private String requestOrigin;
    private String msisdn;
    private String language;
    private String  accountNumber;
    private String invoiceNumber;
    private String currencyCode;
    private Double amount;
    private String description;
    private String billingServiceID;
    private String  billHash;
    private String paymentChannel;
    private String paymentCode;

}
