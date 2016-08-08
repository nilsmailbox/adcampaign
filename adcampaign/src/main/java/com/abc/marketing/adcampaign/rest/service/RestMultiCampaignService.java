package com.abc.marketing.adcampaign.rest.service;



import javax.ws.rs.Path;

@Path("/multiad")
public class RestMultiCampaignService {

	/*private static HashMap<MultiAdInfoVo, MultiAdInfoVo> adStorage = new HashMap<MultiAdInfoVo,MultiAdInfoVo>();
	public static final String ACTIVE_CAMPAINGN_EXISTS_ERROR= "There is aleady an active campaign";
	public static final String NO_ACTIVE_CAMPAINGN_EXISTS_ERROR= "No active ad campaigns exist for the specified partner";
	
	@POST
	@Path("/ad")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createUpdateCampaign(MultiAdInfoVo adInfo) {

		MultiAdInfoVo currentAd;
		synchronized(RestMultiCampaignService.class){	
			currentAd = adStorage.get(adInfo);
		}
		
		if(currentAd == null || currentAd.hasExpired()){
			adStorage.put(adInfo,adInfo);
			System.out.println(adStorage.get(adInfo.getPartner_id()));
			return Response.status(201).build();
		}else{
			return Response.status(409).entity(ACTIVE_CAMPAINGN_EXISTS_ERROR).build();
		}
		
	}
	
	
	@GET
	@Path("/ad/{partnerId}")
	@Produces("application/json")
	public Response getCampaign(@PathParam("partnerId") String partnerId) {

		MultiAdInfoVo currentAd;
		currentAd = adStorage.get(partnerId);
		
		if(currentAd == null || currentAd.hasExpired()){
			return Response.status(404).entity(NO_ACTIVE_CAMPAINGN_EXISTS_ERROR).build();
		}else{
			return Response.status(200).entity(currentAd).build();
		}
		
	}
	
	@GET
	@Path("/ads")
	@Produces("application/json")
	public Response getCampaigns() {
		return Response.status(200).entity(adStorage.values()).build();
	}*/
}