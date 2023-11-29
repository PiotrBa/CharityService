package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Override
    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public Optional<Institution> findInstitutionsById(Long id) {
        return institutionRepository.findById(id);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    public Optional<Institution> updateInstitution(Long id, Institution newInstitution) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            institution.setName(newInstitution.getName());
            institution.setDescription(newInstitution.getDescription());
            return Optional.of(institutionRepository.save(institution));
        }
        return Optional.empty();
    }

    @Override
    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }
}
