package com.rnsoftech.repository;/*
 * @Created 22/04/2024 - 22:06
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
