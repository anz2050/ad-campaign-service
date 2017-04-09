package com.anz.ad.campaign.service;

import java.util.List;

import com.anz.ad.campaign.domain.PartnerAd;

public interface PartnerAdService {
	
	public PartnerAd save(PartnerAd partnerAd);
	
	public List<PartnerAd> getAllPartnerAd();
	
	public PartnerAd getPartnerAdById(String partnerAdId);
	
	public PartnerAd update(String partnerAdId, PartnerAd partnerAd);
	
	public boolean delete(String partnerAdId);
	
}
