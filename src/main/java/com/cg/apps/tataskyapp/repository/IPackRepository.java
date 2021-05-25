package com.cg.apps.tataskyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.apps.tataskyapp.entities.Pack;

@Repository
public interface IPackRepository extends JpaRepository<Pack, Long>{
	
	@Query("from Pack ORDER BY cost ASC")
	List<Pack> findPacksInAscendingOrderByCost();

	@Query("from Pack ORDER BY daysValidity ASC")
	List<Pack> findPacksInAscendingOrderByDaysValidity();

	@Query("from Pack where cost>=:amount")
	List<Pack> findPacksGreaterThanAmount(@Param("amount") Double cost);

	@Query(nativeQuery=true, value="select * from pck where id IN(select pack_id from Recharge group by pack_id having count(*)>0) order by count(*) ")
	List<Pack> popularPacks();
	
}
