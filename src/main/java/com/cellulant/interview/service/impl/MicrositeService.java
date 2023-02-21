package com.cellulant.interview.service.impl;


import com.cellulant.interview.dto.ChargeRequest;
import com.cellulant.interview.dto.DynamicResponse;
import com.cellulant.interview.exception.CellulantBaseException;
import com.cellulant.interview.exception.NotFoundException;
import com.cellulant.interview.service.MicrositeServiceInterface;
import com.cellulant.interview.utils.PaymentMasterData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MicrositeService implements MicrositeServiceInterface {

    @Value("${base.url.microsite}")
    private String baseUri;

    @Value("${end.point.paylink}")
    private String baselink;

    @Value("${point.charge}")
    private String chargeapi;


    private final Logger logger = LoggerFactory.getLogger("Microsite service logger");


    @Override
    public DynamicResponse getDynamic() throws CellulantBaseException {

        String uri = baseUri + baselink;
        RestTemplate restTemplate = new RestTemplate();
        DynamicResponse result = restTemplate.getForObject(uri, DynamicResponse.class);

        ChargeRequest chargeRequest = new ChargeRequest(result.getBill().getMsisdn(), PaymentMasterData.SAFKE.name(),
                PaymentMasterData.M_PESA.name(), PaymentMasterData.USSD_PUSH.name(), result.getBiller().getCountry(),
                PaymentMasterData.TINGG_SUBSCRIPTIONS_WEB.name(), result.getBill().getMsisdn(), "en", result.getBill().getAccountNumber(),
                "", result.getBill().getCurrencyCode(), result.getBill().getDueAmount(), "payment for a bill",
                result.getBill().getBillingServiceID(), result.getBill().getBillID(), "paybill", "589036");

        // send post request
        logger.info(chargeRequest.toString());
        postDynamic(chargeRequest);
        return result;
    }

    /**
     * Post data after
     *
     * @param chargeRequest
     * @return
     * @throws NotFoundException
     */
    @Override
    public String postDynamic(ChargeRequest chargeRequest) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        try {

            String uriPost = baseUri + chargeapi;
            ResponseEntity<String> result = restTemplate.postForEntity(uriPost, chargeRequest, String.class);
            return result.toString();

        } catch (Exception e) {

            throw new NotFoundException(e.getLocalizedMessage(), e);
        }
    }
}
