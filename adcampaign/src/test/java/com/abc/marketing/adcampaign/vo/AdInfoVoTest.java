package com.abc.marketing.adcampaign.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class AdInfoVoTest {

	public Random r = new Random();

	public static final String PARTNER_ID_VAR_NAME = "partner_id";
	public static final String CONTENT_VAR_NAME = "ad_content";
	public static final String DURATION_VAR_NAME = "duration";
	
	
	public static final String PARTNER_ID_VAL = UUID.randomUUID().toString();
	public static final String CONTENT_VAL = UUID.randomUUID().toString();
	public static final int DURATION_VAL = new Random().nextInt();

	
	
	@Test
	public void testAdInfoVo() {
		
		AdInfoVo adInfoVo = new AdInfoVo();
		Whitebox.setInternalState(adInfoVo, PARTNER_ID_VAR_NAME, PARTNER_ID_VAL);
		assertSame(Whitebox.getInternalState(adInfoVo, PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		
	}

	@Test
	public void testAdInfoVoStringIntString() {
		AdInfoVo adInfoVo = new AdInfoVo(PARTNER_ID_VAL, DURATION_VAL, CONTENT_VAL);
		assertSame(Whitebox.getInternalState(adInfoVo, PARTNER_ID_VAR_NAME),PARTNER_ID_VAL);
		assertEquals(Whitebox.getInternalState(adInfoVo, DURATION_VAR_NAME),new Integer(DURATION_VAL));
		assertSame(Whitebox.getInternalState(adInfoVo, CONTENT_VAR_NAME),CONTENT_VAL);
	}

	@Test
	public void testGetPartner_id() {
		AdInfoVo adInfoVo = new AdInfoVo();
		Whitebox.setInternalState(adInfoVo, PARTNER_ID_VAR_NAME, PARTNER_ID_VAL);
		assertSame(adInfoVo.getPartner_id(),PARTNER_ID_VAL);
	}

	@Test
	public void testGetDuration() {
		AdInfoVo adInfoVo = new AdInfoVo();
		Whitebox.setInternalState(adInfoVo, DURATION_VAR_NAME, DURATION_VAL);
		assertTrue(adInfoVo.getDuration() == DURATION_VAL);
	}

	@Test
	public void testGetAd_content() {
		AdInfoVo adInfoVo = new AdInfoVo();
		Whitebox.setInternalState(adInfoVo, CONTENT_VAR_NAME, CONTENT_VAL);
		assertSame(adInfoVo.getAd_content(),CONTENT_VAL);
	}

}
