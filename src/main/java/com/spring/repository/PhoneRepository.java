package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.model.Phone;

/**
 * Class PhoneRepository
 * 
 * @author 56949
 *
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {

	/**
	 * FindByCitycode
	 *
	 * @param cityCode - int
	 * @return Phone data
	 */
	Phone findByCityCode(int cityCode);
	
}

