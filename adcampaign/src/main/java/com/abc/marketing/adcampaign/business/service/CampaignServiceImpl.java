package com.abc.marketing.adcampaign.business.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.abc.marketing.adcampaign.business.service.exceptions.ActiveAdExists;
import com.abc.marketing.adcampaign.business.service.utilities.VoEntityConversionUtil;
import com.abc.marketing.adcampaign.storage.Storage;
import com.abc.marketing.adcampaign.storage.StorageImpl;
import com.abc.marketing.adcampaign.storage.exceptions.ActiveAdExistsStorageException;
import com.abc.marketing.adcampaign.vo.AdInfoVo;

@Service("CampaignService")
public class CampaignServiceImpl implements CampaignService{
	
	@Autowired
	@Qualifier("storage")
	private Storage storage;
	
	
	public void createUpdateAd(AdInfoVo adInfoVo) throws ActiveAdExists {
		
		try{
			storage.save(VoEntityConversionUtil.convertToEntity(adInfoVo));
		}catch(ActiveAdExistsStorageException ucv){
			throw new ActiveAdExists();
		}
	}
	
	public AdInfoVo getActiveAd(String partnerId) {
		
		return VoEntityConversionUtil.convertToVo(storage.get(partnerId));
		
	}
	
	public Collection<AdInfoVo> getAllAds() {
		
		return VoEntityConversionUtil.convertCollectionToVo(storage.getAll());
		
	}
}
