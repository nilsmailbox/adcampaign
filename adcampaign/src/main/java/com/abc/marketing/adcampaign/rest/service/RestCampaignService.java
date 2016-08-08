package com.abc.marketing.adcampaign.rest.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.abc.marketing.adcampaign.business.service.CampaignService;
import com.abc.marketing.adcampaign.business.service.exceptions.ActiveAdExists;
import com.abc.marketing.adcampaign.spring.SpringApplicationContext;
import com.abc.marketing.adcampaign.vo.AdInfoVo;


@Path("")
public class RestCampaignService {

	private CampaignService campaignService;
	
	{
		campaignService = (CampaignService)SpringApplicationContext.getBean("CampaignService");
	}
	
	@POST
	@Path("/ad")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createUpdateCampaign(AdInfoVo adInfoVo) {

		
		try{
			
			campaignService.createUpdateAd(adInfoVo);
		
		}catch(ActiveAdExists aae){
			return Response.status(409).build();
		}
		return Response.status(201).build();
	}
	
	
	@GET
	@Path("/ad/{partnerId}")
	@Produces("application/json")
	public Response getCampaign(@PathParam("partnerId") String partnerId) {
		
		AdInfoVo currentAd;
		currentAd = campaignService.getActiveAd(partnerId);
		
		if(currentAd == null){
			return Response.status(404).build();
		}else{
			return Response.status(200).entity(currentAd).build();
		}
		
	}
	
	@GET
	@Path("/ads")
	@Produces("application/json")
	public Response getCampaigns() {
		return Response.status(200).entity(campaignService.getAllAds()).build();
	}
}