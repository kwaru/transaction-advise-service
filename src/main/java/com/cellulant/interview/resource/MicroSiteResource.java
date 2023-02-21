package com.cellulant.interview.resource;

import com.cellulant.interview.dto.DynamicResponse;
import com.cellulant.interview.exception.CellulantBaseException;
import com.cellulant.interview.service.impl.MicrositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/microsite/payment-advices")
@RestController
public class MicroSiteResource {

    private final MicrositeService micrositeService;


    @Autowired
    public MicroSiteResource(MicrositeService micrositeService) {
        this.micrositeService = micrositeService;
    }


    @GetMapping("/")
    public ResponseEntity<DynamicResponse> callMicrosite() throws CellulantBaseException {

        DynamicResponse dynamicResponse = micrositeService.getDynamic();

        return new ResponseEntity<>(dynamicResponse, HttpStatus.OK);
    }

}
