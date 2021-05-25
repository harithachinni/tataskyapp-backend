package com.cg.apps.tataskyapp.services;

import java.util.List;

import com.cg.apps.tataskyapp.entities.Pack;

public interface IPackService {
	
	Pack add(Pack pack);

	Pack findPackById(Long id);

	List<Pack> findPacksInAscendingOrderByCost();

	List<Pack> findPacksInAscendingOrderByDaysValidity(Integer daysValidity);

	List<Pack> findPacksGreaterThanAmount(Double amount);

	 List <Pack> popularPacks();

	Pack update(Pack pack);

	void deleteByPackId(Long id);
}
