package com.myhome.rest;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.myhome.cassandra.MyHome;


@Component
public class RestClientService {
	
	RestTemplate restTemplate= new RestTemplate();
	
	
	public ResponseEntity<String> getMyHomeResult(String url,MyHome myHome) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MyHome> entity = new HttpEntity<MyHome>(myHome, headers);

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
		return result;
	}

}
