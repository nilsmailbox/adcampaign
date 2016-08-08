package com.abc.marketing.adcampaign.storage.entities;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public final class AdInfoEntity {

	private String partnerId;
	private int duration;
	Calendar expirationDate; // gets a calendar using the default time zone and locale.
	private String adContent;
	
	
	public AdInfoEntity(String partner_id,int duration,String ad_content){

		if(StringUtils.isEmpty(partner_id)){
			throw new IllegalArgumentException("partner_id must not be null");
		}
		
		if(duration <=0 ){
			throw new IllegalArgumentException("duration must be greater than 0");
		}
		
		if(StringUtils.isEmpty(ad_content)){
			throw new IllegalArgumentException("ad_content must not be null");
		}
		
		this.partnerId = partner_id;
		this.duration = duration;
		expirationDate = Calendar.getInstance();
		expirationDate.add(Calendar.SECOND, duration);
		this.adContent = ad_content;

	}
	
	public String getPartner_id() {
		return partnerId;
	}

	public int getDuration() {
		return duration;
	}

	public String getAd_content() {
		return adContent;
	}
	
	public Calendar getExpirationDate() {
		return (Calendar)expirationDate.clone();
	}

	public boolean hasExpired(){
		
		return expirationDate.before(Calendar.getInstance());
	}

}