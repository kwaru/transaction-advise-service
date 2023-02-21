package com.cellulant.interview.utils;

/**
 * enum for constant variables
 */
public enum PaymentMasterData {
    SAFKE("SAFKE"),
    M_PESA("M-PESA"),
    USSD_PUSH("USSD_PUSH"),
    TINGG_SUBSCRIPTIONS_WEB("TINGG_SUBSCRIPTIONS_WEB"),
    ;

    private final String description;

    PaymentMasterData(String description) {
        this.description = description;
    }
}
