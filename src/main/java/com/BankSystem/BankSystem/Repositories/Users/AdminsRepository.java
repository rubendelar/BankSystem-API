package com.BankSystem.BankSystem.Repositories.Users;

import com.BankSystem.BankSystem.Models.Users.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository <Admins, Integer> {
}
