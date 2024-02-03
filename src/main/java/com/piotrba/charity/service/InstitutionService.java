package com.piotrba.charity.service;

import com.piotrba.charity.entity.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {

    List<Institution> findAllActiveInstitutions();
    List<Institution> findAllInactiveInstitutions();
    Optional<Institution> findInstitutionsById(Long id);
    Institution saveInstitution(Institution institution);
    Optional<Institution> updateInstitution(Long id, Institution institution);
    void deleteInstitution(Long id);
}
