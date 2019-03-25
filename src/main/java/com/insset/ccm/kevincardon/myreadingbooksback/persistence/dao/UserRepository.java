package com.insset.ccm.kevincardon.myreadingbooksback.persistence.dao;

import com.insset.ccm.kevincardon.myreadingbooksback.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

        User findByUsername(final String username);
}
