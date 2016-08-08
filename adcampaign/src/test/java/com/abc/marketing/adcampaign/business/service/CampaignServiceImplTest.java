package com.abc.marketing.adcampaign.business.service;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.abc.marketing.adcampaign.business.service.exceptions.ActiveAdExists;
import com.abc.marketing.adcampaign.business.service.utilities.VoEntityConversionUtil;
import com.abc.marketing.adcampaign.storage.StorageImpl;
import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.storage.exceptions.ActiveAdExistsStorageException;
import com.abc.marketing.adcampaign.vo.AdInfoVo;

import static test.utilities.TestConstants.AdInfoEntity.*;
import static test.utilities.TestConstants.AdInfoVo.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ VoEntityConversionUtil.class, StorageImpl.class})
public class CampaignServiceImplTest {

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
	public void testCreateUpdateAd() {
		
		
		PowerMock.mockStatic(VoEntityConversionUtil.class);
		EasyMock.expect(VoEntityConversionUtil.convertToEntity(ADD_INFO_VO)).andReturn(ADD_INFO_ENTITY);
		
		StorageImpl storageMock = PowerMock.createMock(StorageImpl.class);
		try {
			storageMock.save(ADD_INFO_ENTITY);
		} catch (ActiveAdExistsStorageException e1) {
			e1.printStackTrace();
		}
		EasyMock.expectLastCall();
		CampaignService campaignService = new CampaignServiceImpl();
		Whitebox.setInternalState(campaignService, "storage", storageMock);
		
		PowerMock.replayAll();
		try {
			campaignService.createUpdateAd(ADD_INFO_VO);
			
		} catch (ActiveAdExists e) {
			e.printStackTrace();
		}
		PowerMock.verifyAll();
	}

	@Test
	public void testGetActiveAd() {
		
		StorageImpl storageMock = PowerMock.createMock(StorageImpl.class);
		EasyMock.expect(storageMock.getActiveAd(ADD_INFO_ENTITY.getPartner_id())).andReturn(ADD_INFO_ENTITY);
		
		PowerMock.mockStatic(VoEntityConversionUtil.class);
		EasyMock.expect(VoEntityConversionUtil.convertToVo(ADD_INFO_ENTITY)).andReturn(ADD_INFO_VO);
		
		CampaignService campaignService = new CampaignServiceImpl();
		Whitebox.setInternalState(campaignService, "storage", storageMock);
		
		PowerMock.replayAll();
		AdInfoVo  adInfoVoActual = campaignService.getActiveAd(ADD_INFO_ENTITY.getPartner_id());
		PowerMock.verifyAll();
		
		assertSame(ADD_INFO_VO, adInfoVoActual);
		
	}

	@Test
	public void testGetAllAds() {
		
		

	}

}
