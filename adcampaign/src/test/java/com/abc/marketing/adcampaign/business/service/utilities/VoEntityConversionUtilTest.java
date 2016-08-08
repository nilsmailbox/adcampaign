package com.abc.marketing.adcampaign.business.service.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static test.utilities.TestConstants.AdInfoEntity.ENTITY_CONTENT_VAR_NAME;
import static test.utilities.TestConstants.AdInfoEntity.ENTITY_EXPIRATION_VAR_NAME;
import static test.utilities.TestConstants.AdInfoEntity.ENTITY_PARTNER_ID_VAR_NAME;
import static test.utilities.TestConstants.AdInfoValue.CONTENT_VAL;
import static test.utilities.TestConstants.AdInfoValue.DURATION_VAL;
import static test.utilities.TestConstants.AdInfoValue.PARTNER_ID_VAL;
import static test.utilities.TestConstants.AdInfoVo.VO_CONTENT_VAR_NAME;
import static test.utilities.TestConstants.AdInfoVo.VO_DURATION_VAR_NAME;
import static test.utilities.TestConstants.AdInfoVo.VO_PARTNER_ID_VAR_NAME;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

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

import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.vo.AdInfoVo;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Calendar.class, AdInfoEntity.class, VoEntityConversionUtil.class})
public class VoEntityConversionUtilTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if(DURATION_VAL <-1){
			DURATION_VAL = DURATION_VAL *-1;
		}
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
	public void testConvertToEntity() {
		AdInfoVo adInfoVo = new AdInfoVo(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		
		Calendar calendar = Calendar.getInstance();
		
		long initialTime = calendar.getTimeInMillis();
		long expectedExpirationTime = initialTime + DURATION_VAL*1000L;
		PowerMock.mockStatic(Calendar.class);
		EasyMock.expect(Calendar.getInstance()).andReturn(calendar);
		
		PowerMock.replayAll();
		AdInfoEntity adInfoEntity = VoEntityConversionUtil.convertToEntity(adInfoVo);
		PowerMock.verifyAll();
				
		assertTrue(((Calendar)Whitebox.getInternalState(adInfoEntity, ENTITY_EXPIRATION_VAR_NAME)).getTimeInMillis() == expectedExpirationTime);
		assertSame(Whitebox.getInternalState(adInfoEntity, ENTITY_PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		assertSame(Whitebox.getInternalState(adInfoEntity, ENTITY_CONTENT_VAR_NAME),CONTENT_VAL);
		
	}
	
	@Test
	public void testConvertToEntity_Duration_Max_Integer() {
		
		AdInfoVo adInfoVo = new AdInfoVo(PARTNER_ID_VAL, Integer.MAX_VALUE, CONTENT_VAL);
		
		Calendar calendar = Calendar.getInstance();
		
		long initialTime = calendar.getTimeInMillis();
		long expectedExpirationTime = initialTime + Integer.MAX_VALUE*1000L;
		PowerMock.mockStatic(Calendar.class);
		EasyMock.expect(Calendar.getInstance()).andReturn(calendar);
		
		PowerMock.replayAll();
		AdInfoEntity adInfoEntity = VoEntityConversionUtil.convertToEntity(adInfoVo);
		PowerMock.verifyAll();
				
		assertTrue(((Calendar)Whitebox.getInternalState(adInfoEntity, ENTITY_EXPIRATION_VAR_NAME)).getTimeInMillis() == expectedExpirationTime);
		assertSame(Whitebox.getInternalState(adInfoEntity, ENTITY_PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		assertSame(Whitebox.getInternalState(adInfoEntity, ENTITY_CONTENT_VAR_NAME),CONTENT_VAL);
		
	}

	@Test
	public void testConvertToVo() {
		
		AdInfoEntity adInfoEntity = new AdInfoEntity(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		
		AdInfoVo adInfoVo = VoEntityConversionUtil.convertToVo(adInfoEntity);
		
		assertEquals(Whitebox.getInternalState(adInfoVo,VO_DURATION_VAR_NAME ), new Integer(DURATION_VAL));
		assertSame(Whitebox.getInternalState(adInfoVo, VO_PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		assertSame(Whitebox.getInternalState(adInfoVo, VO_CONTENT_VAR_NAME),CONTENT_VAL);
		
	}
	
	@Test
	public void testConvertToVo_Duration_Max_Integer() {
		
		AdInfoEntity adInfoEntity = new AdInfoEntity(PARTNER_ID_VAL, Integer.MAX_VALUE, CONTENT_VAL);
		
		AdInfoVo adInfoVo = VoEntityConversionUtil.convertToVo(adInfoEntity);
		
		assertEquals(Whitebox.getInternalState(adInfoVo,VO_DURATION_VAR_NAME ), new Integer(Integer.MAX_VALUE));
		assertSame(Whitebox.getInternalState(adInfoVo, VO_PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		assertSame(Whitebox.getInternalState(adInfoVo, VO_CONTENT_VAR_NAME),CONTENT_VAL);
		
	}
	
	@Test
	public void convertCollectionToVo() {
		
		
		
		AdInfoEntity adInfoEntity = new AdInfoEntity("1", 1, "c1");
		AdInfoEntity adInfoEntity2 = new AdInfoEntity("2", 2, "c2");
		Collection<AdInfoEntity> adInfoEntityCollection = new ArrayList<AdInfoEntity>();
		adInfoEntityCollection.add(adInfoEntity);
		adInfoEntityCollection.add(adInfoEntity2);
		
		AdInfoVo adInfoVo = VoEntityConversionUtil.convertToVo(adInfoEntity);
		AdInfoVo adInfoVo2 = VoEntityConversionUtil.convertToVo(adInfoEntity);
		PowerMock.mockStaticPartial(VoEntityConversionUtil.class, "convertToVo");
		EasyMock.expect(VoEntityConversionUtil.convertToVo(adInfoEntity)).andReturn(adInfoVo);
		EasyMock.expect(VoEntityConversionUtil.convertToVo(adInfoEntity2)).andReturn(adInfoVo2);
		
		PowerMock.replayAll();
		Collection<AdInfoVo> adInfoVoCollection = VoEntityConversionUtil.convertCollectionToVo(adInfoEntityCollection);
		PowerMock.verifyAll();
		assertTrue(adInfoVoCollection.contains(adInfoVo));
		assertTrue(adInfoVoCollection.contains(adInfoVo2));
		
		
	}
	
	

}
