package com.texasstate.id.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@UtilityClass
public class ApplicantUtil {

    final String VALID = "valid";

    public boolean validateApplicantDetails(RestTemplate restTemplate, Integer ssn, Long phone, String email) throws ExecutionException, InterruptedException {

        CompletableFuture<String> validateSsn =  CompletableFuture.completedFuture(
                restTemplate.getForObject("http://localhost:8080/validate/ssn/"+ssn, String.class));
        CompletableFuture<String> validatePhone =  CompletableFuture.completedFuture(
                restTemplate.getForObject("http://localhost:8080/validate/phone/"+phone, String.class));
        CompletableFuture<String> validateEmail =  CompletableFuture.completedFuture(
                restTemplate.getForObject("http://localhost:8080/validate/email/"+email, String.class));
        CompletableFuture.allOf(validateSsn, validatePhone, validateEmail).join();

        return (validateSsn.get().equals(VALID) && validatePhone.get().equals(VALID) && validateEmail.get().equals(VALID));
    }
}
