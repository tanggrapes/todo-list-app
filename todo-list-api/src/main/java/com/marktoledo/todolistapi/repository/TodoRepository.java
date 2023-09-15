package com.marktoledo.todolistapi.repository;

import com.marktoledo.todolistapi.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
