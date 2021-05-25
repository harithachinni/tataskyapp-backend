package com.cg.apps.tataskyapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.services.IPackService;





//@ExtendWith({SpringExtension.class})
//@DataJpaTest
//@Import(IPackServiceImpl.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class PackServiceImplTest {
	

	@Autowired
	private IPackService service;
	@MockBean
	private EntityManager em;
	
	@Test
	public void testAddPack() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("abc","def"));
		Pack pckSaved = service.add(pck);
	    Assertions.assertEquals(pckSaved.getChannels(), pck.getChannels());	
	    
	}

	@Test
	public void testFindPackById() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
		em.persist(pck);
		Long id = pck.getId();
		Pack pckFound = service.findPackById(id);
		Assertions.assertEquals(pckFound.getCost(), pck.getCost());
		
	}
	
	@Test
	public void testUpdatePack() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
		Pack pckUpdated = service.update(pck);
	    Assertions.assertEquals(pckUpdated.getChannels(), pck.getChannels());	
	    
	}
	
//	@Test
//	public void testDeleteByPackId() {
//		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
//		em.persist(pck);
//		Long id = pck.getId();
//		service.deleteByPackId(id);
//		verify(em, times(1)).deleteByPackId(pck);
//	}
	
	@Test
	public void testfindPacksInAscendingOrderByCost() {
		Pack pck1 = new Pack(600.0, 29,"Pack for three month","Entertainment Pack", Arrays.asList("Star Movies","Sony Sab"));
		Pack pck2 = new Pack(290.0, 29,"Pack for a month","Kid Pack", Arrays.asList("Nick","disney"));
		Pack pck3 = new Pack(470.0, 29,"Pack for two month","Sports Pack", Arrays.asList("star sports","Sony Ten HD"));
		em.persist(pck1);
		em.persist(pck2);
		em.persist(pck3);
		List<Pack> pckFound1 = service.findPacksInAscendingOrderByCost();
		List<Pack> pckFound2 = service.findPacksInAscendingOrderByCost();
		Assertions.assertEquals(pckFound1.get(1), pckFound2.get(1));
		
		
	}
	
	@Test
	public void testfindPacksInAscendingOrderByDaysValidity() {
		Pack pck4 = new Pack(600.0, 29,"Pack for three month","Entertainment Pack", Arrays.asList("Star Movies","Sony Sab"));
		Pack pck5 = new Pack(290.0, 29,"Pack for a month","Kid Pack", Arrays.asList("Nick","disney"));
		Pack pck6 = new Pack(470.0, 29,"Pack for two month","Sports Pack", Arrays.asList("star sports","Sony Ten HD"));
		em.persist(pck4);
		em.persist(pck5);
		em.persist(pck6);
		List<Pack> pckFound3 = service.findPacksInAscendingOrderByDaysValidity(29);
		List<Pack> pckFound4 = service.findPacksInAscendingOrderByDaysValidity(29);
		Assertions.assertEquals(pckFound3.get(2), pckFound4.get(2));
	}

}