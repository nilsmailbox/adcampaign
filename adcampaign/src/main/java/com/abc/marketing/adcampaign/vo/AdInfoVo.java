package com.abc.marketing.adcampaign.vo;

import java.util.Calendar;

public class AdInfoVo {

	private String partner_id;
	private int duration;
	Calendar expirationDate; // gets a calendar using the default time zone and locale.
	private String ad_content;
	
	AdInfoVo(){
		
	}
	
	public AdInfoVo(String partner_id,int duration,String ad_content){

		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;

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