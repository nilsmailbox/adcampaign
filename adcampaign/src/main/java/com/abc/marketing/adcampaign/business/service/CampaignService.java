package com.abc.marketing.adcampaign.business.service;

import java.util.Collection;

import com.abc.marketing.adcampaign.business.service.exceptions.ActiveAdExists;
import com.abc.marketing.adcampaign.vo.AdInfoVo;



public interface CampaignService {

	public void createUpdateAd(AdInfoVo adInfoVo) throws ActiveAdExists;
	
	public AdInfoVo getActiveAd(String partnerId);
	
	public Collection<AdInfoVo> getAllAds();
}
