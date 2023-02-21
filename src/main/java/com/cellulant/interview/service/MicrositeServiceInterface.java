package com.cellulant.interview.service;

import com.cellulant.interview.dto.ChargeRequest;
import com.cellulant.interview.dto.DynamicResponse;
import com.cellulant.interview.exception.CellulantBaseException;
import com.cellulant.interview.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface MicrositeServiceInterface {

   DynamicResponse getDynamic() throws CellulantBaseException;
    String postDynamic(ChargeRequest chargeRequest) throws  NotFoundException;
}
