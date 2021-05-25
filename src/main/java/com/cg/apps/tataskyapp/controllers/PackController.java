package com.cg.apps.tataskyapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.dto.PackDetails;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.services.IPackService;
import com.cg.apps.tataskyapp.util.PackUtil;

@RestController
@RequestMapping("/pack")
@Validated
public class PackController {

	@Autowired
	private IPackService pService;

	@Autowired
	private PackUtil appUtil;


//for admin to add a new pack,sends http 201 created success status response
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public PackDetails add(@RequestBody PackRequestController requestData) {
		System.out.println("req data: " + requestData);
		Pack pack = new Pack(requestData.getCost(), requestData.getDaysValidity(), requestData.getDescription(),
				requestData.getPlanName(), requestData.getChannels());
		System.out.println("req came: " + pack);
		Pack pck = pService.add(pack);
		PackDetails details = PackUtil.toDetails(pck);
		return details;

	}

//to update an existing pack	
		@ResponseStatus(code = HttpStatus.CREATED)
		@PutMapping("/update")
		public Pack update(@RequestBody Pack pack) {
			Pack pck = pService.update(pack);
			return pck;
		}	

//to find a pack by it's id	
	@GetMapping("/by/id/{id}")
	public PackDetails findPackById(@PathVariable("id") Long id) {
		Pack pck = pService.findPackById(id);
		PackDetails details = PackUtil.toDetails(pck);
		return details;
	}

//returns a list of packs in ascending order of their costs	i.e. least to most expensive pack
	@GetMapping("/by/cost")
	public List<PackDetails> findByCost() {
		List<Pack> list = pService.findPacksInAscendingOrderByCost();
		List<PackDetails> pckList = PackUtil.toDetails(list);
		return pckList;
	}

//returns a list with pack details as per increasing order of their validity 	
	@GetMapping("/by/daysValidity/{daysValidity}")
	public List<PackDetails> findBydaysValidity(@PathVariable("daysValidity") Integer daysValidity) {
		List<Pack> list = pService.findPacksInAscendingOrderByDaysValidity(daysValidity);
		List<PackDetails> pckList = PackUtil.toDetails(list);
		return pckList;
	}

//returns a list of packs having amount greater than a specific amount entered by the client/user
	@GetMapping("/by/greaterAmount/{amount}")
	public List<PackDetails> findByAmount(@PathVariable("amount") Double amount) {
		List<Pack> list = pService.findPacksGreaterThanAmount(amount);
		List<PackDetails> pckList = PackUtil.toDetails(list);
		return pckList;
	}

//returns a list of packs in order of their popularity i.e. having maximum to minimum no. of recharges 	
//	@GetMapping("/by/popularPacks")
//	public List<String> findBypopularity(){
//		List<Pack>pck= pService.popularPacks();
//		//List<PackDetails> pckList = AppUtil.toDetails(list);
//		return pck;
//		
//	}



//to delete a pack using it's id 	
	@DeleteMapping("/delete/by/id/{id}")
	public void deleteByPackId(@PathVariable("id") Long id) {
		pService.deleteByPackId(id);
		System.out.println("Pack Deleted successfully");
	}

}
