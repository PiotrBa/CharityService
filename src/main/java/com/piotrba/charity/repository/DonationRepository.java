package com.piotrba.charity.repository;

import com.piotrba.charity.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM (d.quantity) FROM Donation d")
    Integer sumAllQuantities();

    List<Donation> findByusername(String username);
}
