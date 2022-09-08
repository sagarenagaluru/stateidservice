package com.texasstate.id.service;

import com.texasstate.id.advice.coustomExceptions.ApplicantNotCreatedException;
import com.texasstate.id.dto.ApplicantRequest;
import com.texasstate.id.entity.Applicant;
import com.texasstate.id.repository.ApplicantRepository;
import com.texasstate.id.util.ApplicantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public Applicant saveApplicant(ApplicantRequest applicantRequest) throws ExecutionException, InterruptedException, ApplicantNotCreatedException {
        Integer ssn = applicantRequest.getSsn();
        Long phone = applicantRequest.getPhone();
        String email = applicantRequest.getEmail();

        if(!ApplicantUtil.validateApplicantDetails(restTemplate, ssn, phone, email))
            throw (new ApplicantNotCreatedException("Unable to create Applicant with given details"));

        Applicant applicant = Applicant.build(0, applicantRequest.getName(), ssn,
                        phone, email, applicantRequest.getBirthDate());
        return repository.save(applicant);
    }


}
