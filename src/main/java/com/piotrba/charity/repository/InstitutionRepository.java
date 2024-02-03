package com.piotrba.charity.repository;

import com.piotrba.charity.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    List<Institution> findByActiveFalse();
    List<Institution> findByActiveTrue();
}
