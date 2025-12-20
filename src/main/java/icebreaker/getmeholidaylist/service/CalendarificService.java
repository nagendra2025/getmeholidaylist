package icebreaker.getmeholidaylist.service;

import java.util.List;

//CalendarificService.java
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import icebreaker.getmeholidaylist.dto.CountryDto;
import icebreaker.getmeholidaylist.dto.SupportedCountriesResponse;

@Service
public class CalendarificService {

 private final RestTemplate restTemplate = new RestTemplate();

 // put your key in application.properties and inject it in real code
 private final String apiKey = "fmP0s169Wiy7UTYVShkIb4VGKsVWJbrO";
 private final String baseUrl = "https://calendarific.com/api/v2/countries";

 public List<CountryDto> getSupportedCountries() {
	    String url = UriComponentsBuilder
	            .fromUriString(baseUrl)
	            .queryParam("api_key", apiKey)
	            .build(true)
	            .toUriString();

	    SupportedCountriesResponse res =
	            restTemplate.getForObject(url, SupportedCountriesResponse.class);

	    if (res == null || res.getResponse() == null || res.getResponse().getCountries() == null) {
	        return List.of();
	    }
	    return res.getResponse().getCountries();
	}

}

