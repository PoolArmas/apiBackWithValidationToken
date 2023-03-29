package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.model.User;

/**
 * Class Repository
 * 
 * @author 56949
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
  
  User findByName(String name);
}
