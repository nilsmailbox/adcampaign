package com.abc.marketing.adcampaign.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abc.marketing.adcampaign.storage.entities.AdInfoEntity;
import com.abc.marketing.adcampaign.storage.exceptions.ActiveAdExistsStorageException;


/*
 * created this class so if any maintenance adds new services and needs access to this common storage
 * */
@Repository("storage")
public class StorageImpl implements Storage {

	private Map<String, AdInfoEntity> adStorage = new HashMap<String,AdInfoEntity>(25);
	
	public void save(AdInfoEntity adInfoEntity) throws ActiveAdExistsStorageException{
		
		AdInfoEntity currentAd;
		//Prevent overwriting active ad campaign
		synchronized(adStorage){	
			currentAd = adStorage.get(adInfoEntity.getPartner_id());
			if(currentAd == null || currentAd.hasExpired()){
				adStorage.put(adInfoEntity.getPartner_id(), adInfoEntity);
				return;
			}
		}
		throw new ActiveAdExistsStorageException();
	}
	
	public Collection<AdInfoEntity> getAll(){
		
		Collection<AdInfoEntity> adInfoEntityCollection = new ArrayList<AdInfoEntity>(adStorage.size());
		//need to copy and synchronized due to 
		synchronized(adStorage){
			for(AdInfoEntity adEntity:adStorage.values()){
				adInfoEntityCollection.add(adEntity);
			}
		}
		return adInfoEntityCollection;
	}
	
	
	public AdInfoEntity get(String partnerId){
		AdInfoEntity adInfoEntity = adStorage.get(partnerId);
		return (adInfoEntity == null || adInfoEntity.hasExpired()) ? null : adInfoEntity ;
	}
}
