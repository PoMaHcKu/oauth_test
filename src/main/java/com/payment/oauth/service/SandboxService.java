package com.payment.oauth.service;

import com.payment.oauth.model.Payload;
import com.payment.oauth.model.PaymentInitiationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SandboxService {

    @POST("/payment-requests")
    Call<PaymentInitiationResponse> paymentRequest(@Header("Authorization") String auth, @Body Payload payload);

}
