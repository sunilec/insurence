package com.ies.repo;

import com.ies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
 
	
}
