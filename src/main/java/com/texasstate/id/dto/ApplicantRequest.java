package com.texasstate.id.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ApplicantRequest {

    @NotNull(message = "username shouldn't be null")
    private String name;

    //@Pattern(regexp = "^\\d{9}$",message = "SSN Must be 9 digit number")
    @NotNull(message = "SSN Must be 9 digit number")
    private Integer ssn;

    //@Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
    @NotNull(message = "Mobile number can not be null or empty")
    private Long phone;

    @Email(message = "Invalid email address")
    @NotNull
    private String email;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "birthDate cannot be null")
    private LocalDate birthDate;
}
