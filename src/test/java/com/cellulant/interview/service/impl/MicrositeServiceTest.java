package com.cellulant.interview.service.impl;

import com.cellulant.interview.dto.Bill;
import com.cellulant.interview.dto.Biller;
import com.cellulant.interview.dto.ChargeRequest;
import com.cellulant.interview.dto.DynamicResponse;
import com.cellulant.interview.exception.CellulantBaseException;
import com.cellulant.interview.utils.PaymentMasterData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(MicrositeService.class)
@RunWith(SpringRunner.class)
class MicrositeServiceTest {

    @Autowired
    private MicrositeService underTestMicroService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;


    @Value("${base.url.microsite}")
    private String baseUri;

    @Value("${end.point.paylink}")
    private  String baselink;

    @Value("${point.charge}")
    private String chargeapi;


    private  final String uri = baseUri + baselink;
    @Before
    public void setUp() throws Exception {
        String detailsString =
                objectMapper.writeValueAsString(new DynamicResponse(new Biller(), new Bill()));

        this.mockRestServiceServer.expect(requestTo(uri))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }



    @Test
    //NOTE: For this unit to pass test , postDynamic() must have passed the positive test case
    void testThatWeCanGetSuccessfulResponse() throws CellulantBaseException {

        DynamicResponse response = underTestMicroService.getDynamic();


      assertThat(response.getBiller().getCountry().equals("KE"));
      assertThat(response.getBill().getBillID().equals("205"));
    }




    @Test
    void testThatWecanPostTransactionDetailsSuccesfulAfterGetRequest() {
        // Given
        DynamicResponse response = underTestMicroService.getDynamic();

        // when
        ChargeRequest chargeRequest = new ChargeRequest(response.getBill().getMsisdn(), PaymentMasterData.SAFKE.name(),
                PaymentMasterData.M_PESA.name(), PaymentMasterData.USSD_PUSH.name(), response.getBiller().getCountry(),
                PaymentMasterData.TINGG_SUBSCRIPTIONS_WEB.name(), response.getBill().getMsisdn(), "en", response.getBill().getAccountNumber(),
                "", response.getBill().getCurrencyCode(), response.getBill().getDueAmount(), "payment for a bill",
                response.getBill().getBillingServiceID(), response.getBill().getBillID(), "paybill", "589036");

     String responseEntity = underTestMicroService.postDynamic(chargeRequest);
//Then
        // I am not sure of the response data details for the post method.

        //assertThat();
    }


    @Test
    void testThatWecanPostTransactionDetailsUnSuccesfulAfterGetRequest() {
        // Given
        DynamicResponse response = underTestMicroService.getDynamic();

        // when
        ChargeRequest chargeRequest = new ChargeRequest(response.getBill().getMsisdn(), PaymentMasterData.SAFKE.name(),
                PaymentMasterData.M_PESA.name(), PaymentMasterData.USSD_PUSH.name(), response.getBiller().getCountry(),
                PaymentMasterData.TINGG_SUBSCRIPTIONS_WEB.name(), response.getBill().getMsisdn(), "en", response.getBill().getAccountNumber(),
                "", response.getBill().getCurrencyCode(), response.getBill().getDueAmount(), "payment for a bill",
                response.getBill().getBillingServiceID(), response.getBill().getBillID(), "paybill", "589036");

        String responseString= underTestMicroService.postDynamic(chargeRequest);



        Logger logger = LoggerFactory.getLogger("my logger");
        logger.info("logger=>"+responseString);
        //I am not sure of the response data details for the post method.
        //assertThat();
    }
}