package com.cg.apps.tataskyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.repository.IAccountRepository;

import com.cg.apps.tataskyapp.services.IAccountServiceImpl;
import com.cg.apps.tataskyapp.exception.AccountNotFoundException;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;

@SpringBootTest
public class AccountServiceTest {
	org.slf4j.Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

	@Autowired
	private IAccountServiceImpl accService;
	@MockBean
	private IAccountRepository accRepo;
	Pack p1;
	User user1;
	User user2;
	User user3;

	@BeforeEach
	public void init() {
		logger.info("******** Test Account Methods *****");

		user1 = new User("Sparsh26", "Sparsh", "Aggarwal", "Sparsh@1998", "user");
		user2 = new User("Madhav26", "Madhav", "Goel", "MAdhav@1998", "admin");
		user3 = new User("assem25", "Aseem", "Sharma", "Aseem@1998", "user");

		Recharge rec1 = new Recharge(100.00, 30, "Sports Entertainment", "Sport", LocalDate.of(2020, 12, 20), null,
				true);

		Recharge rec2 = new Recharge(250.00, 28, "Hindi Entertainment", "Hindi Serials", LocalDate.of(2021, 05, 10),
				null, false);

		List<String> channel = new ArrayList<>();
		channel.add("Star Sport");
		channel.add("Star Sport1");
		channel.add("Tens Sport");
		channel.add("DD Sport");

		Pack p1 = new Pack(200.0, 31, "Hindi Entertainment", "Gold", null);
		List<Recharge> r1 = new ArrayList<>();
		r1.add(rec1);
		r1.add(rec2);
	}

	@Test
	public void testAddAccount() {
		logger.info("******** Test New Account Added  *****");

		Account a1 = new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1);
		when(accRepo.save(a1)).thenReturn(a1);

		assertEquals(a1.getAccountId(), accService.add(a1).getAccountId());

	}

	@Test
	public void testFindById() throws AccountNotFoundException {
		logger.info("********Test Get By Id  Account  *****");
		Optional<Account> a1 = Optional.of(new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1));

		when(accRepo.save(a1.get())).thenReturn(a1.get());
		when(accRepo.findById(a1.get().getAccountId())).thenReturn(a1);

		assertEquals(a1.get().getAccountId(), accService.findById((long) 1).getAccountId());

	}

	@Test
	public void testUpdateAccount() throws AccountNotFoundException {
		logger.info("********Test  Account Updated *****");
		Account a1 = new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1);
		when(accRepo.existsById(a1.getAccountId())).thenReturn(true);
		 a1.setUser(user2);
		when(accRepo.save(a1)).thenReturn(a1);
		assertEquals(a1.getUser(), accService.update(a1));
		

//		assertEquals(a1.getCurrentPack(), accService.update(a1).getCurrentPack());
		assertEquals(a1.getRecharges(), accService.update(a1).getRecharges());
		assertEquals(a1.getRegisteredDate(), accService.update(a1).getRegisteredDate());
		assertEquals(a1.getUser(), accService.update(a1).getUser());

	}

	@Test
	public void testCountTotalAccounts() {
		logger.info("********  Test Total Number of Accounts in Account Table *****");
		Account a1 = new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1);
		Account a2 = new Account((long) 2, user2, null, LocalDate.of(2021, 06, 02), null, p1);
		Account a3 = new Account((long) 3, user3, null, LocalDate.of(2021, 05, 15), null, p1);
		
		when(accRepo.save(a1)).thenReturn(a1);
		when(accRepo.save(a2)).thenReturn(a2);
		when(accRepo.save(a3)).thenReturn(a3);
		

		assertEquals(accRepo.count(), accService.countAccounts());

	}

	@Test
	public void testDeleteAccount() {
		logger.info("******** Test Account Deleted from Account Table *****");
		Account a1 = new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1);
		when(accRepo.save(a1)).thenReturn(a1);
		when(accRepo.existsById(a1.getAccountId())).thenReturn(true);
		accService.deleteByAccountId((long) 1);
		verify(accRepo, times(1)).deleteById((long) 1);

	}
	@Test
	public void testCountAccountInPeriod() {
		logger.info("******** Test Total Number Of Account in Betwwen Start Date and End Date *****");

		Account a1 = new Account((long) 1, user1, null, LocalDate.of(2020, 12, 20), null, p1);
		Account a2 = new Account((long) 2, user2, null, LocalDate.of(2021, 06, 02), null, p1);
		Account a3 = new Account((long) 3, user3, null, LocalDate.of(2021, 05, 15), null, p1);
		when(accRepo.save(a1)).thenReturn(a1);
		when(accRepo.save(a2)).thenReturn(a2);
		when(accRepo.save(a3)).thenReturn(a3);
		assertEquals(accRepo.count(),accService.countCreatedAccountsInPeriod(LocalDate.of(2019, 05, 20),LocalDate.of(2021, 06, 15)));
	}
	
	

}
