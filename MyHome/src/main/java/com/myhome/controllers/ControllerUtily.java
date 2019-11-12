package com.myhome.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.hazelcast.core.HazelcastInstance;
import com.myhome.cassandra.MyHome;
import com.myhome.rest.RestClientService;

@Component
public class ControllerUtily {
	
	private final Logger logger = LoggerFactory.getLogger(ControllerUtily.class);
	private final HazelcastInstance hazelcastInstance;
	
	@Value("${rest.restemplate.url}")
	private String url;
	
	@Autowired
	ControllerUtily(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
	
	@Autowired
	RestClientService restClientService;
	
	public String writeDateToHazelCast(MyHome home) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(home.getId());
		buffer.append(home.getAge());
		buffer.append(home.getJob());
		buffer.append(home.getName());
		Map<String,String> hazelcastMap= hazelcastInstance.getMap("my-Map");
		hazelcastMap.put(home.getId(), buffer.toString());
		logger.info(buffer.toString());
		ResponseEntity<String> resutl=restClientService.getMyHomeResult(url, home);
		System.out.print("sfadaasfdsfdsafsaf"+resutl.getBody().toString());
		return buffer.toString();
	}
	
	public Map<String,String> readAllDate() {
		Map<String,String> hazelcastMap= hazelcastInstance.getMap("my-Map");
		return hazelcastMap;
		
	}
	
	public String readIdDate(String key) {
		Map<String,String> hazelcastMap= hazelcastInstance.getMap("my-Map");
		
		return hazelcastMap.get(key);
		
	}

}
