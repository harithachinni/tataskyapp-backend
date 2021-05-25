package com.cg.apps.tataskyapp.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;

@Repository
public interface IRechargeRepository extends JpaRepository<Recharge, Integer> {

	// Recharge add(Recharge recharge);

	// Recharge update(Recharge recharge);

	@Query("from Recharge where account=:account ORDER BY purchasedDate desc")
	List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(@Param("account") Account account);

	@Query("SELECT count(*) from Recharge where account=:account")
	int rechargesForUserCount(@Param("account") Account account);

	@Query("from Recharge where purchasedDate between :stDate and :enDate")
	List<Recharge> findAllRechargesInPeriod(@Param("stDate") LocalDate startDate, @Param("enDate") LocalDate endDate);

	@Query(value = "select count(rech.id) from Recharge rech where rech.purchasedDate between :stDate and :enDate")
	int countRechargesInPeriod(@Param("stDate") LocalDate startDate, @Param("enDate") LocalDate endDate);

	/**
	 * calculates revenue by add of all recharges
	 */
	@Query(value = "select sum(amount) from Recharge where purchasedDate between :stDate and :enDate")
	double totalRevenueInPeriod(@Param("stDate") LocalDate startDate, @Param("enDate") LocalDate endDate);

	@Query(value = "select count(rech.id) from Recharge rech where rech.pack=:pack")
	int rechargesCount(@Param("pack") Pack pack);

	Recharge findById(Long id);

	// List<Recharge> findAll();

}
