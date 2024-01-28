package com.piotrba.charity.repository;

import com.piotrba.charity.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"userDonations"})
    User getWithDonationsByUsername(String username);

    List<User> findByActiveTrue();

    List<User> findByActiveFalse();

    User findUserByEmail(String email);

    Optional<User> findByResetToken(String resetToken);
}
