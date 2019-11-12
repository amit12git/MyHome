package com.myhome.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hazelcast.core.HazelcastInstance;
import com.myhome.cassandra.MyHome;

@Component
public class ControllerUtily {
	
	private final Logger logger = LoggerFactory.getLogger(ControllerUtily.class);
	private final HazelcastInstance hazelcastInstance;
	
	@Autowired
	ControllerUtily(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
	
	public String writeDateToHazelCast(MyHome home) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(home.getId());
		buffer.append(home.getAge());
		buffer.append(home.getJob());
		buffer.append(home.getName());
		Map<String,String> hazelcastMap= hazelcastInstance.getMap("my-Map");
		hazelcastMap.put(home.getId(), buffer.toString());
		logger.info(buffer.toString());
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
