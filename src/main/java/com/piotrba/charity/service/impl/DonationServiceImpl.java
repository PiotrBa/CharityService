package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{

    private final DonationRepository donationRepository;


    @Override
    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Optional<Donation> findDonationsById(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Optional<Donation> updateDonation(Long id, Donation newDonation) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()){
            Donation donation = optionalDonation.get();
            donation.setAmount(newDonation.getAmount());
            donation.setQuantity(newDonation.getQuantity());
            donation.setCategories(newDonation.getCategories());
            donation.setInstitutions(newDonation.getInstitutions());
            donation.setStreet(newDonation.getStreet());
            donation.setCity(newDonation.getCity());
            donation.setZipCode(newDonation.getZipCode());
            donation.setPickUpDate(newDonation.getPickUpDate());
            donation.setPickUpTime(newDonation.getPickUpTime());
            donation.setPickUpComment(newDonation.getPickUpComment());
            return Optional.of(donationRepository.save(donation));
        }
        return Optional.empty();
    }

    @Override
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    @Override
    public Integer sumAllQuantities() {
        return donationRepository.sumAllAmount();
    }

    @Override
    public Integer sumAllAmount() {
        return donationRepository.sumAllQuantities();
    }

    @Override
    public Long countAllDonations() {
        return donationRepository.count();
    }
}
