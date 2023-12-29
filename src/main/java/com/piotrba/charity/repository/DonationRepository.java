package com.piotrba.charity.repository;

import com.piotrba.charity.entity.Donation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM (d.quantity) FROM Donation d")
    Integer sumAllQuantities();

    @Query("SELECT COUNT(d) FROM Donation d WHERE d.user.username = :username")
    Long countDonationsByUserUsername(String username);

    @Query("SELECT SUM(d.quantity) FROM Donation d WHERE d.user.username = :username")
    Integer sumQuantitiesByUserUsername(String username);
    List<Donation> findByUserUsername(String username);

    @Query("SELECT d FROM Donation d")
    @EntityGraph(attributePaths = {"user"})
    List<Donation> findAllWithUser();
}
