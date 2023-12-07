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

  /**
   *
   * @param email - string
   * @return User Data
   */
  User findByEmail(String email);

  /**
   *
   * @param name - string
   * @return User Data
   */
  User findByName(String name);
}
