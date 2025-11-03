package com.example.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testproject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
