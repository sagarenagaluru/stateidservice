package com.texasstate.id.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;
    private Integer ssn;
    private Long phone;
    private String email;
    private LocalDate birthDate;
}
