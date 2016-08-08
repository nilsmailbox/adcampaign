package test.utilities;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class TestConstants {
	
	public static class AdInfoVo{
	
		
		public static final String VO_PARTNER_ID_VAR_NAME = "partner_id";
		public static final String VO_CONTENT_VAR_NAME = "ad_content";
		public static final String VO_DURATION_VAR_NAME = "duration";
		public static final com.abc.marketing.adcampaign.vo.AdInfoVo ADD_INFO_VO = new com.abc.marketing.adcampaign.vo.AdInfoVo(AdInfoValue.PARTNER_ID_VAL, AdInfoValue.DURATION_VAL, AdInfoValue.CONTENT_VAL);

	}
	
	
	public static class AdInfoEntity{
		
		public static final String ENTITY_PARTNER_ID_VAR_NAME = "partnerId";
		public static final String ENTITY_CONTENT_VAR_NAME = "adContent";
		public static final String ENTITY_DURATION_VAR_NAME = "duration";
		public static final String ENTITY_EXPIRATION_VAR_NAME = "expirationDate";
		public static final com.abc.marketing.adcampaign.storage.entities.AdInfoEntity ADD_INFO_ENTITY = new com.abc.marketing.adcampaign.storage.entities.AdInfoEntity(AdInfoValue.PARTNER_ID_VAL, AdInfoValue.DURATION_VAL, AdInfoValue.CONTENT_VAL);
		
	}
	
	public static class AdInfoValue{
		
		public static final String PARTNER_ID_VAL = UUID.randomUUID().toString();
		public static final String CONTENT_VAL = UUID.randomUUID().toString();
		public static int aRandom = new Random(Integer.MAX_VALUE).nextInt();
		public static final int DURATION_VAL = aRandom != 0 ? Math.abs(aRandom) : 1;
		

	}
	
	public static class StorageEntity{
		public static final String AD_STORAGE_VAR_NAME = "adStorage";
	}
	
	
	public static class CalendarUtil{
		public static Calendar getPastDate(){
			final Calendar pastExpirationDate = Calendar.getInstance();
			pastExpirationDate.add(Calendar.SECOND, -1);
			return pastExpirationDate;
		}
	}

}
