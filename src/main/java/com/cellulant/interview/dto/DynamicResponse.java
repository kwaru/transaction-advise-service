package com.cellulant.interview.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Dynamic GET response
 */
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DynamicResponse {
     private  Biller biller;
     private Bill bill;
    // private BillingServiceResponse billingServiceResponse;


}
