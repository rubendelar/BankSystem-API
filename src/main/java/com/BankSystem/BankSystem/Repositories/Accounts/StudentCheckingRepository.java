package com.BankSystem.BankSystem.Repositories.Accounts;

import com.BankSystem.BankSystem.Models.Accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository <StudentChecking, Integer> {
}
