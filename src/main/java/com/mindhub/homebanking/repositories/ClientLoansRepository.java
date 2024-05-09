package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.ClientLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLoansRepository extends JpaRepository<ClientLoans, Long> {
}
