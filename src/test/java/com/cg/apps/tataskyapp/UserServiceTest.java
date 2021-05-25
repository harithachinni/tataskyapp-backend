package com.cg.apps.tataskyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.User;
import com.cg.apps.tataskyapp.repository.IUserRepository;
import com.cg.apps.tataskyapp.services.IUserService;
import com.cg.apps.tataskyapp.services.IUserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private IUserServiceImpl service;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@MockBean
	private IUserRepository repo;
	
	User u1;
	User u2;
	User u3;
	User u4;
	User u5;
	Optional<User> u;
	
	@BeforeEach
	public void init() {
		
		u1 = new User((long) 1,"aseemsharma30","Aseem","Sharma","asesha","admin",true,null);
		u2 = new User("sparsh26", "Sparsh", "Agarwal", "spaaga", "admin");
		u3 = new User("vidit19", "Vidit", "Sharma", "vidsha", "user");
		u4 = new User((long) 4,"abhiarun26", "Abhiraj", "Pant", "abhpan", "user",true);
		u5 = new User((long) 5,"dev95", "Devanshu", "Sharma", "devsha", "user",true);
		
		u = Optional.of(u1);

	}
	
	//test add user
	@Test
	public void testAddUser() {
				
		logger.info("******** Test Register Methods *****");
		
		when(repo.save(u2)).thenReturn(u2);
		
		assertEquals(repo.findByUsername("sparsh26"), service.register(u2));
		
	}
	
	// test find user by id
	@Test
	public void testFindUserById() {
		
		logger.info("******** Test Find User by Id *****");
		
		when(repo.findById(u.get().getId())).thenReturn(u);
		assertEquals(u.get().getUsername(), service.findById((long) 1).getUsername());
	}

	// test find user by username
	@Test
	public void testFindUserByUsername() {
		
		logger.info("******** Find User by Username *****");
		
		when(repo.findByUsername(u3.getUsername())).thenReturn(u3);
		assertEquals(u3.getUsername(), service.findByUsername("vidit19").getUsername());
	}
	
//	@Test
//	public void testupdateAdmin() {
//		
//		when(repo.existsById(u4.getId())).thenReturn(true);
//		when(repo.save(u5)).thenReturn(u5);
//		assertEquals(u5.getUsername(), service.update(u5).getUsername());
//		assertEquals(u5.getFirstName(), service.update(u5).getFirstName());
//		assertEquals(u5.getLastName(), service.update(u5).getLastName());
//		assertEquals(u5.getPassword(), service.update(u5).getPassword());
//		assertEquals(u5.getRole(), service.update(u5).getRole());
//		assertEquals(u5.isActive(), service.update(u5).isActive());
//
//		
//	}
	
	// test delete user by id
	@Test
	public void testDeleteUser() {
		
		logger.info("******** Test Delete User *****");
		
		when(repo.existsById(u1.getId())).thenReturn(true);
		repo.delete(u1);
		verify(repo, times(1)).delete(u1);
	}
	
	

	
}
