package com.abc.marketing.adcampaign.storage;

import java.util.Collection;

import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.storage.exceptions.ActiveAdExistsStorageException;

public interface Storage {

	public void save(AdInfoEntity adInfoEntity) throws ActiveAdExistsStorageException;
	
	public Collection<AdInfoEntity> getAll();
	
	public AdInfoEntity getActiveAd(String partnerId);

}
