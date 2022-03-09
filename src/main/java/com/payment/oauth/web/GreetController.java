package com.payment.oauth.web;

import com.payment.oauth.model.Payload;
import com.payment.oauth.model.PaymentInitiationResponse;
import com.payment.oauth.model.User;
import com.payment.oauth.service.SandboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.security.Principal;

@Controller
public class GreetController {

    @Autowired
    private SandboxService sandboxService;

    @GetMapping
    public ModelAndView indexPage() {
        ModelAndView index = new ModelAndView("index");
        index.addObject("payload", new Payload());
        return index;
    }

    @PostMapping("/payment")
    public ModelAndView sendPayment(@ModelAttribute Payload payload, Principal principal) throws IOException {

        OAuth2AuthenticationToken oAuthToken = (OAuth2AuthenticationToken) principal;
        String token = ((User) oAuthToken.getPrincipal()).getToken();
        String authHeaderValue = "Bearer " + token;
        Call<PaymentInitiationResponse> paymentResponse = sandboxService.paymentRequest(authHeaderValue, payload);
        Response<PaymentInitiationResponse> executeResponse = paymentResponse.execute();

        ModelAndView statusView = new ModelAndView("status");
        if (executeResponse.isSuccessful()) {
            PaymentInitiationResponse body = executeResponse.body();
            if (body != null) {
                String status = body.getStatus();
                statusView.addObject("status", status);
            }
        } else {
            String message = executeResponse.message();
            statusView.addObject("error", message);
        }

        return statusView;
    }

}