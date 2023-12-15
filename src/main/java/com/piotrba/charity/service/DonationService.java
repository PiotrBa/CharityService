package com.piotrba.charity.service;

import com.piotrba.charity.entity.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    List<Donation> findAllDonations();
    Optional<Donation> findDonationsById(Long id);
    Donation saveDonation(Donation donation);
    Optional<Donation> updateDonation(Long id, Donation donation);
    void deleteDonation(Long id);
    Integer sumAllQuantities();
    Long countAllDonations();
    Long countDonationsByUser(String username);

    Integer sumQuantitiesByUser(String username);

}
