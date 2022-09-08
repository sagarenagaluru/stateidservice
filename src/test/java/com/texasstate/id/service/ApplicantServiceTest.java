package com.texasstate.id.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texasstate.id.advice.coustomExceptions.ApplicantNotCreatedException;
import com.texasstate.id.config.ExecutorConfig;
import com.texasstate.id.dto.ApplicantRequest;
import com.texasstate.id.entity.Applicant;
import com.texasstate.id.repository.ApplicantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicantServiceTest {
    @MockBean
    private ApplicantRepository applicantRepository;

    @MockBean
    private ApplicantService applicantService;

    @MockBean
    private ExecutorConfig executorConfig;

    @MockBean
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    Applicant applicant;

    @BeforeEach
    public void setUp() {
        applicant = Applicant.build(1, "Sagar", 123456789, 9876543210L,
                "name@mail.com", LocalDate.of(2022, 04, 20));
    }

    @Test
    public void testSaveApplicantWithName() throws ApplicantNotCreatedException, ExecutionException, InterruptedException {
        ApplicantRequest applicantRequest = ApplicantRequest.build("Sagar", 123456789, 9876543210L,
                "name@mail.com", LocalDate.of(2022, 04, 20));
        Applicant applicants = applicantService.saveApplicant(applicantRequest);
        Assertions.assertEquals(applicant.getName(), "Sagar");
    }
}
