package com.anz.ad.campaign.web;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anz.ad.campaign.domain.PartnerAd;
import com.anz.ad.campaign.service.PartnerAdService;
import com.anz.ad.campaign.web.PartnerAdController;


@RunWith(SpringRunner.class)
@WebMvcTest(PartnerAdController.class)
public class PartnerAdControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PartnerAdService partnerAdService;
	
	private final String URL = "/ad";

	@Test
	public void testCreatePartnerAd() throws Exception {
		
		// prepare data and mock's behavior
		PartnerAd partnerAdStub = new PartnerAd("Braun", 60, LocalDateTime.now(), "Series 7 - Smart Shaver");
		when(partnerAdService.save(any(PartnerAd.class))).thenReturn(partnerAdStub);
		
		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(partnerAdStub)))
				.andReturn();
		
		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);
		
		// verify that service method was called once
		verify(partnerAdService).save(any(PartnerAd.class));
		
		PartnerAd partnerAdResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), PartnerAd.class);
		assertNotNull(partnerAdResult);
		assertEquals("Braun", partnerAdResult.getPartnerId());
		
		
	}

}
