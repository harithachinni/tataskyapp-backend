package com.cg.apps.tataskyapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.apps.tataskyapp.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	@Query("from User where username=:uname")
	User findByUsername(@Param("uname") String username);
	
}
