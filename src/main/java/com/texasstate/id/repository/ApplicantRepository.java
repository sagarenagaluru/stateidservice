package com.texasstate.id.repository;

import com.texasstate.id.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository  extends JpaRepository<Applicant,Integer> {
}
