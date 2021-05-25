package com.cg.apps.tataskyapp.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tataskyapp.exception.PackNotFoundException;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.repository.IPackRepository;
import com.cg.apps.tataskyapp.services.IPackService;
import com.cg.apps.tataskyapp.services.IPackServiceImpl;

import ch.qos.logback.classic.Logger;


@Service
@Transactional
public class IPackServiceImpl implements IPackService {

	@Autowired
	private IPackRepository sDao;

	private org.jboss.logging.Logger logger = LoggerFactory.logger(IPackServiceImpl.class);

	@Override
	public Pack add(Pack pack) {
		logger.info("******** Pack Created*****");
		Pack pck = sDao.save(pack);
		return pck;
	}

	@Override
	public Pack findPackById(Long id) {
		logger.info("******** Pack found for id *******" + id);
		Optional<Pack> opt = sDao.findById(id);
		if (!opt.isPresent()) {
			throw new PackNotFoundException("Pack not found for id:" + id);
		}
		Pack pck = opt.get();
		return pck;
	}

	@Override
	public List<Pack> findPacksInAscendingOrderByCost() {
		logger.info("*******findingPacksInAscendingOrderByCost*******");
		List<Pack> list = sDao.findPacksInAscendingOrderByCost();
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<Pack> findPacksInAscendingOrderByDaysValidity(Integer daysValidity) {
		logger.info("*******findingPacksInAscendingOrderByAmount*******");
		List<Pack> list = sDao.findPacksInAscendingOrderByDaysValidity();
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<Pack> findPacksGreaterThanAmount(Double amount) {
		logger.info("*******findingPacksGreaterThanAmount*******");
		List<Pack> list = sDao.findPacksGreaterThanAmount(amount);
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<Pack> popularPacks() {
		List<Pack> list = sDao.popularPacks();
		return list;
	}

	@Override
	public Pack update(Pack pack) {
		Pack pck = sDao.save(pack);
		return pck;
	}

	@Override
	public void deleteByPackId(Long id) {
		logger.info("******** Pack Deleted for id *******" + id);
		Optional<Pack> opt = sDao.findById(id);
		if (!opt.isPresent()) {
			throw new PackNotFoundException("Pack not found");

		}

		sDao.deleteById(id);

	}

}
