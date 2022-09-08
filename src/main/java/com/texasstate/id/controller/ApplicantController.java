package com.texasstate.id.controller;

import com.texasstate.id.dto.ApplicantRequest;
import com.texasstate.id.entity.Applicant;
import com.texasstate.id.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
public class ApplicantController {
    @Autowired
    private ApplicantService service;

    @PostMapping("/saveApplicant")
    public ResponseEntity<Applicant> saveUser(@RequestBody @Valid ApplicantRequest applicantRequest) throws ExecutionException, InterruptedException, Exception {
        return new ResponseEntity<>(service.saveApplicant(applicantRequest), HttpStatus.CREATED);
    }

    @GetMapping("/validate/ssn/{ssn}")
    public ResponseEntity<String> getFooById(@PathVariable Integer ssn) {
        return ResponseEntity.ok("valid");
    }

    @GetMapping("/validate/phone/{phone}")
    public ResponseEntity<String> getFooById(@PathVariable Long phone) {
        return ResponseEntity.ok("valid");
    }

    @GetMapping("/validate/email/{email}")
    public ResponseEntity<String> getFooById(@PathVariable String email) {
        return ResponseEntity.ok("valid");
    }

}
