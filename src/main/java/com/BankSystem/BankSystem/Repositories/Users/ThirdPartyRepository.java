package com.BankSystem.BankSystem.Repositories.Users;

import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Integer> {
}
