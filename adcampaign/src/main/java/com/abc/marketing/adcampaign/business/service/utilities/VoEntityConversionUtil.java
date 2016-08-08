package com.abc.marketing.adcampaign.business.service.utilities;

import java.util.ArrayList;
import java.util.Collection;

import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.vo.AdInfoVo;

public class VoEntityConversionUtil {
	
	public static AdInfoEntity convertToEntity(AdInfoVo adInfoVo){
		return new AdInfoEntity(adInfoVo.getPartner_id(), adInfoVo.getDuration(), adInfoVo.getAd_content());
	}
	
	public static AdInfoVo convertToVo(AdInfoEntity adInfoEntity){
		return new AdInfoVo(adInfoEntity.getPartner_id(), adInfoEntity.getDuration(), adInfoEntity.getAd_content());
	}
	
	public static Collection<AdInfoVo> convertCollectionToVo(Collection<AdInfoEntity> adInfoEntities){
		
		Collection<AdInfoVo> voList = new ArrayList<AdInfoVo>(adInfoEntities.size());
		
		for (AdInfoEntity adInfo : adInfoEntities) {
			voList.add(convertToVo(adInfo));
		}
		return voList;
	}

}
