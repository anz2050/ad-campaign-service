package com.anz.ad.campaign.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.ad.campaign.domain.PartnerAd;
import com.anz.ad.campaign.repositories.PartnerAdRepository;
import com.anz.ad.campaign.util.DateTimeUtility;

@Service
public class PartnerAdService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdService.class);
	
	private PartnerAdRepository partnerAdRepository;
	
	@Autowired
	public PartnerAdService(PartnerAdRepository partnerAdRepository) {
		this.partnerAdRepository = partnerAdRepository;
	}
	
	public PartnerAd save(PartnerAd partnerAd) {
		LOGGER.info("+++++++++ PartnerAdService | save(PartnerAd partnerAd) +++++++++++++"); 
		return partnerAdRepository.save(partnerAd);
	}	
	
	public List<PartnerAd> getAllPartnerAd() {
		LOGGER.info("+++++++++ PartnerAdService | getAllPartnerAd() +++++++++++++"); 
		return (List<PartnerAd>) partnerAdRepository.findAll();
	}
	
	public PartnerAd getPartnerAdById(String partnerId) {
		LOGGER.info("+++++++++ PartnerAdService | getPartnerAdById(String partnerId) +++++++++++++"); 
		return partnerAdRepository.findOne(partnerId);
	}
	
	public void delete(Serializable id) {
		LOGGER.info("+++++++++ PartnerAdService | delete(Serializable id) +++++++++++++");
		partnerAdRepository.delete((String) id);
	}
	
	
	public boolean isPartnerCampaignActive(String partnerId) {
		
		boolean isCampaignActive = false;
		PartnerAd partnerAd = partnerAdRepository.findOne(partnerId);
		
		if (partnerAd != null) {
			LocalDateTime campaingDuration = DateTimeUtility.addSecondsToCreatedDateTime(partnerAd.getCreationDate(),
					partnerAd.getDuration());

			LocalDateTime currentTime = LocalDateTime.now();
			if (DateTimeUtility.isCurrentTimeIsLessThanCampignTime(currentTime, campaingDuration)) {
				isCampaignActive = true;
			}
		}
		return isCampaignActive;
	}

}
