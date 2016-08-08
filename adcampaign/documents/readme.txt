To Run this application
	deploy adcampaign.war on j2ee application server and access following are the URLS.
		To create new ad -POST at http://localhost:8081/adcampaign/rest/ad
		To view a partner ad -GET at http://localhost:8081/adcampaign/rest/ad/{partner_id}
		To view all ads - GET at  http://localhost:8081/adcampaign/rest/ads
		


Bonus assignments

	Advantage and Disadvantages of persistence mechanism
	
		Advantages of of persistence mechanism
			Fast access time O(1) performance
			Fast save O(1)
			Scalable by adding processors
			Enforces constraint like there are no two active ads for a partner so no application code can violate that constraint by mistake 
		
		Disadvantages
			Can't survive machine restart
			Not scalable by adding machines 
			No transaction support and so hard to create dao layer. If doa layer is created then business code will move to 
			dao layer in case multiple service are added later on can't really synchronize at service layer
			Does not store old data like relational database table
			Due to lack of time could not implement positive duration constraint
			Does not support constraint like not null, not empty. Could have done it but the entity it stores takes care of it
			Does not perform well. not concurrent like relational tables.
		
		

	Describe a fault tolerant deployment topology for your application, and the types of failures it would and would not be resilient to.
		
		Before this topology is done. It must be made sure that application is scalable by adding machines at different levels
		-There will be four layers of deployment topology 1. Load balancers 2. Web Servers 3.Application Servers 4. Database servers
		-Load balancers will balance load based on availability of web servers.
		-Have Multiple web servers that can forward according to the availability of application servers
		-Have Multiple application servers  that can forward request according to the availability of application servers
		-Have Multiple Database servers which can be accessed according to availability by application servers 
		-Keep machine at all layers in different geographic locations
		-Allow caching at different levels for read only and where its fine not to have latest data with proper expiration time. 
			For example, client browser can fulfill some of the requests from cache in case of absolutely rare failures of all machine 
				at any level in topology.
		-Wherever possible replicate data.If can't replicate, divide data using proper classification method so not all users are affected 
			by failure of a machine or some.
		
		-Following are advantage of this topology
			-if machine at all layers fail only then becomes unavailable
			-If data are divided on different machines then few users are affected by failure of some machines
			-If possible to replicate then rarely users are affected 
			
		-Following are disadvantages
			-Replication overhead
	



