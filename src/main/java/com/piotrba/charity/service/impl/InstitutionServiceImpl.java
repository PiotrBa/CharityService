package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @Override
    public List<Institution> findAllActiveInstitutions() {
        return institutionRepository.findByActiveTrue();
    }

    @Override
    public List<Institution> findAllInactiveInstitutions() {return institutionRepository.findByActiveFalse();}

    @Override
    public Optional<Institution> findInstitutionsById(Long id) {
        return institutionRepository.findById(id);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        institution.setActive(true);
        return institutionRepository.save(institution);
    }

    @Override
    public Optional<Institution> updateInstitution(Long id, Institution newInstitution) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            institution.setName(newInstitution.getName());
            institution.setDescription(newInstitution.getDescription());
            institution.setActive(newInstitution.getActive());
            return Optional.of(institutionRepository.save(institution));
        }
        return Optional.empty();
    }

    @Override
    public void deleteInstitution(Long id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found"));

        List<Donation> donations = donationRepository.findByInstitutions_Id(id);
        for (Donation donation : donations) {
            donationRepository.delete(donation);
        }
        institutionRepository.delete(institution);
    }

}
