package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;


    @Override
    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Optional<Donation> findDonationsById(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public Donation saveDonation(Donation donation, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            donation.setUser(userOptional.get());
            return donationRepository.save(donation);
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public Optional<Donation> updateDonation(Long id, Donation newDonation) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()){
            Donation donation = optionalDonation.get();
            donation.setQuantity(newDonation.getQuantity());
            donation.setCategories(newDonation.getCategories());
            donation.setInstitutions(newDonation.getInstitutions());
            donation.setStreet(newDonation.getStreet());
            donation.setCity(newDonation.getCity());
            donation.setZipCode(newDonation.getZipCode());
            donation.setPickUpDateAndTime(newDonation.getPickUpDateAndTime());
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
        return donationRepository.sumAllQuantities();
    }

    @Override
    public Long countAllDonations() {
            return donationRepository.count();
    }

    @Override
    public Long countDonationsByUser(String username) {
        return donationRepository.countDonationsByUserUsername(username);
    }

    @Override
    public Integer sumQuantitiesByUser(String username) {
        return donationRepository.sumQuantitiesByUserUsername(username);
    }
}
