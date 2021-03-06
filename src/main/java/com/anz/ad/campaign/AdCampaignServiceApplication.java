package com.anz.ad.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdCampaignServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdCampaignServiceApplication.class);
	
	public static void main(String[] args) {
		LOGGER.trace("Starting Ad Campaign Server...");
		SpringApplication.run(AdCampaignServiceApplication.class, args);
	}
	
	// For initial record insert test
	/*@Bean
	public CommandLineRunner demo(PartnerAdRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new PartnerAd("Kirkland", 60, LocalDateTime.now(), "UltraClean - Odor Eliminating Technology"));
			repository.save(new PartnerAd("P&G", 90, LocalDateTime.now(), "Crest ProHealth - 99% Germ Killer"));
			// fetch all PartnerAd
			LOGGER.info("PartnerAd found with findAll():");
			LOGGER.info("-------------------------------");
			for (PartnerAd partnerAd : repository.findAll()) {
				LOGGER.info(partnerAd.toString());
			}
			LOGGER.info("");
		};
	}*/
}
