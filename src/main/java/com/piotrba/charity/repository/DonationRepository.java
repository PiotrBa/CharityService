package com.piotrba.charity.repository;

import com.piotrba.charity.entity.Donation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM (d.quantity) FROM Donation d")
    Long sumAllQuantities();

    @Query("SELECT COUNT(d) FROM Donation d WHERE d.user.username = :username AND d.awaitingApproval = true AND d.packageReceived = true")
    Long countDonationsByUserUsername(String username);

    @Query("SELECT SUM(d.quantity) FROM Donation d WHERE d.user.username = :username AND d.awaitingApproval = true AND d.packageReceived = true")
    Long sumQuantitiesByUserUsername(String username);

    @Query("SELECT d FROM Donation d")
    @EntityGraph(attributePaths = {"user"})
    List<Donation> findAllWithUser();

    List<Donation> findByCategories_Id(Long categoryId);
    List<Donation> findByInstitutions_Id(Long institutionId);

    @Query("SELECT d FROM Donation d WHERE d.user.username = :username AND d.awaitingApproval = true AND d.packageReceived = true")
    List<Donation> findDonationsByUserWithApprovalAndPackageReceived(String username);

    @Query("SELECT d FROM Donation d WHERE d.user.username = :username AND d.awaitingApproval = true AND d.packageReceived = false")
    List<Donation> findDonationsByUserWithApprovalAndPackageNotReceived(String username);

}
