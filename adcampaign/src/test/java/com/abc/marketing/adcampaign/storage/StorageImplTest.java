package com.abc.marketing.adcampaign.storage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.storage.exceptions.ActiveAdExistsStorageException;

import static test.utilities.TestConstants.StorageEntity.*;
import static test.utilities.TestConstants.AdInfoValue.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testStorage() {
		StorageImpl storage = new StorageImpl();
		assertTrue(Whitebox.getInternalState(storage, AD_STORAGE_VAR_NAME) instanceof HashMap);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testSave() {
		StorageImpl storage = new StorageImpl();
		AdInfoEntity adInfoEntity = new AdInfoEntity(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		try {
			storage.save(adInfoEntity);
		} catch (ActiveAdExistsStorageException e) {
			e.printStackTrace();
		}
		
		assertSame(adInfoEntity,((Map<String, AdInfoEntity>)Whitebox.getInternalState(storage, AD_STORAGE_VAR_NAME)).get(adInfoEntity.getPartner_id()));
	}
	
	@Test
	public void testSave_Entity_Already_Exists() {
		StorageImpl storage = new StorageImpl();
		AdInfoEntity adInfoEntity = new AdInfoEntity(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		try {
			storage.save(adInfoEntity);
		} catch (ActiveAdExistsStorageException e) {
			e.printStackTrace();
		}
		
		try {
			storage.save(adInfoEntity);
			fail("testSave_Entity_Already_Exists failed");
		} catch (ActiveAdExistsStorageException e) {
			
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetAll() {
		StorageImpl storage = new StorageImpl();
		List<AdInfoEntity> adInfoEntityList = new ArrayList<AdInfoEntity>();
		adInfoEntityList.addAll(((Map<String, AdInfoEntity>)Whitebox.getInternalState(storage, AD_STORAGE_VAR_NAME)).values());
		assertEquals(storage.getAll(), adInfoEntityList);

	}
	
	

	@Test
	@SuppressWarnings("unchecked")
	public void testGet() {
		StorageImpl storage = new StorageImpl();
		AdInfoEntity adInfoEntity = new AdInfoEntity(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		
		((Map<String, AdInfoEntity>)Whitebox.getInternalState(storage, AD_STORAGE_VAR_NAME)).put(adInfoEntity.getPartner_id(), adInfoEntity);

		assertSame(adInfoEntity, storage.get(adInfoEntity.getPartner_id()));

	}
	


}
