package com.cellulant.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Biller {
    private String country;
    private BillerConfig billerConfig;
}
