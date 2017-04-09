package com.anz.ad.campaign.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.ad.campaign.domain.PartnerAd;
import com.anz.ad.campaign.exception.AdCampaignAlreadyExistException;
import com.anz.ad.campaign.exception.AdCampaignNotFoundException;
import com.anz.ad.campaign.service.PartnerAdService;



@RestController
public class PartnerAdController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdController.class);

	private PartnerAdService partnerAdService;
	
	@Autowired
	public PartnerAdController(PartnerAdService partnerAdService) {
		this.partnerAdService = partnerAdService;
	}
	
	
	/**
	 * REST Service to CREATE Ad Campaign
	 * @param partnerAd
	 * @return
	 */
	@RequestMapping(value = "/ad", method = RequestMethod.POST)
	public ResponseEntity<PartnerAd> insertPartnerAd(@RequestBody PartnerAd partnerAd) {
		LOGGER.info("/ad/" + "api post request called...");
		
		if(isPartnerActive(partnerAd.getPartnerId())) {
			throw new AdCampaignAlreadyExistException(partnerAd.getPartnerId());
		}
		
		LocalDateTime adCreationDateTime = LocalDateTime.now();
		partnerAd.setCreationDate(adCreationDateTime);
		PartnerAd createdPartnerAd = partnerAdService.save(partnerAd);
		LOGGER.debug("Added:: " + createdPartnerAd);
		return new ResponseEntity<PartnerAd>(createdPartnerAd, HttpStatus.CREATED);
	}
	
	/**
	 * REST Service to GET all Ad Campaign
	 * @return
	 */
	@RequestMapping("/")
	public ResponseEntity<List<PartnerAd>> getAllPartnerAd() {
		List<PartnerAd> partners = (List<PartnerAd>) partnerAdService.getAllPartnerAd();
		if (partners.isEmpty()) {
			LOGGER.debug("No PartnerAd exists");
			return new ResponseEntity<List<PartnerAd>>(HttpStatus.NO_CONTENT);
		}
		LOGGER.debug("Found " + partners.size() + " PartnerAd");
		LOGGER.debug(partners.toString());
		LOGGER.debug(Arrays.toString(partners.toArray()));
		
		return new ResponseEntity<List<PartnerAd>>(partners, HttpStatus.OK);
	}
	
	/**
	 * REST Service to GET a specific Ad Campaign
	 * @param partnerId
	 * @return
	 */
	@RequestMapping(value = "/ad/{partnerId}", method = RequestMethod.GET)
	public ResponseEntity<PartnerAd> getPartnerAd(@PathVariable(value="partnerId") final String partnerId) {
		LOGGER.info("/ad/" + partnerId + "api request called...");
		
		if(isPartnerActive(partnerId)) {
			PartnerAd partnerAd = partnerAdService.getPartnerAdById(partnerId);
			LOGGER.debug("Found PatnerAd: " + partnerAd);
			return new ResponseEntity<PartnerAd>(partnerAd, HttpStatus.OK);
		} else {
			LOGGER.debug("No active ad campaigns exist for the specified partner: {}", partnerId);
			throw new AdCampaignNotFoundException(partnerId);
		}
		
		/*PartnerAd partnerAd = partnerAdService.getPartnerAdById(partnerId);
		if (partnerAd == null) {
			LOGGER.debug("No active ad campaigns exist for the specified partner: {}", partnerId);
			//return new ResponseEntity<PartnerAd>(HttpStatus.NOT_FOUND);
			throw new AdCampaignNotFoundException(partnerId);
		}
		LOGGER.debug("Found PatnerAd: " + partnerAd);
		
		return new ResponseEntity<PartnerAd>(partnerAd, HttpStatus.OK);*/
		//return partnerAdService.getPartnerAd(partnerId);
	}
	
	/**
	 * REST Service to UPDATE a specific Ad Campaign
	 * @param partnerAdId
	 * @param partnerAd
	 * @return
	 */
	@RequestMapping(value = "/ad/{partnerAdId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updatePartnerAd(@PathVariable(value="partnerAdId") String partnerAdId, @RequestBody PartnerAd partnerAd) {
		PartnerAd existingPartnerAd = partnerAdService.getPartnerAdById(partnerAdId);
		if (existingPartnerAd == null) {
			LOGGER.debug("PartnerAd with id " + partnerAdId + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			PartnerAd updatedPartnerAd = partnerAdService.save(existingPartnerAd);
			LOGGER.info("Ad Campaign updated with this value: {}", updatedPartnerAd);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	/**
	 * REST Service to DELETE a specific Ad Campaign
	 * @param partnerAdId
	 * @return
	 */
	@RequestMapping(value = "/{partnerAdId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePartnerAd(@PathVariable("partnerAdId") String partnerAdId) {
		PartnerAd partnerAd = partnerAdService.getPartnerAdById(partnerAdId);
		if (partnerAd == null) {
			LOGGER.debug("PartnerAd with id " + partnerAdId + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			partnerAdService.delete(partnerAdId);
			LOGGER.debug("Ad Campaign with id " + partnerAdId + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	
	private boolean isPartnerActive(String partnerAdId) {
		return partnerAdService.isPartnerCampaignActive(partnerAdId);
	}
	
}
