package com.BankSystem.BankSystem.Repositories.Users;

import com.BankSystem.BankSystem.Models.Users.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository <UserType, Integer> {
}
