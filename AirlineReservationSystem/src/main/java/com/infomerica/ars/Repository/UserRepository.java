package com.infomerica.ars.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infomerica.ars.DAO.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	
}
