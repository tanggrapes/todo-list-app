package com.marktoledo.todolistapi.repository;

import com.marktoledo.todolistapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
