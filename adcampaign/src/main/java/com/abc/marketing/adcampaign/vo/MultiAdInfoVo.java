package com.abc.marketing.adcampaign.vo;

import java.util.Calendar;

public class MultiAdInfoVo {

	private String partner_id;
	private int duration;
	Calendar expirationDate; // gets a calendar using the default time zone and locale.
	private String ad_content;


	public MultiAdInfoVo(){
		
	}
	
	
	public String getPartner_id() {
		return partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public String getAd_content() {
		return ad_content;
	}

}