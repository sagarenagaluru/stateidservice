package com.texasstate.id.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texasstate.id.config.ExecutorConfig;
import com.texasstate.id.entity.Applicant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ApplicantUtilTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Long phone = 9876543210L;
    private String email = "name@gmail.com";
    private Integer ssn = 123456789;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ExecutorConfig executorConfig;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private Applicant applicant;

    @BeforeAll
    public void setUp() {
        applicant = Applicant.build(1, "Sagar", ssn, phone,
                email, LocalDate.of(2022, 04, 20));
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @BeforeEach
    public void init() throws URISyntaxException {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/validate/ssn/"+ssn)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("valid")
                );
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/validate/phone/"+phone)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("valid")
                );
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/validate/email/"+email)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("valid")
                );
    }

    @Test
    public void validateApplicantDetailsTest() throws ExecutionException, InterruptedException {
       boolean validate = ApplicantUtil.validateApplicantDetails(restTemplate, ssn, phone, email);
        Assertions.assertTrue(validate);
    }
}
