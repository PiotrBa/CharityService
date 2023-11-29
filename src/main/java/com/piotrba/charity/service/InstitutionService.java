package com.piotrba.charity.service;

import com.piotrba.charity.entity.Institution;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface InstitutionService {

    List<Institution> findAllInstitutions();
    Optional<Institution> findInstitutionsById(Long id);
    Institution saveInstitution(Institution institution);
    Optional<Institution> updateInstitution(Long id, Institution institution);
    void deleteInstitution(Long id);
}
