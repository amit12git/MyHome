package com.myhome.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.kafka.MyHome;
import com.myhome.kafka.Producer;

@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private final Producer producer;
	
	@Autowired
	MainController(Producer producer) {
        this.producer = producer;
    }

	
	@RequestMapping("/")
	public String start(){
		return "hello.html";
	}
	
	@RequestMapping("/healthcheck")
	@ResponseBody
	public String getHealthCheck() {
		return "My home Message is Working";
	}
	
    @RequestMapping(value ="/publish",method = RequestMethod.POST)
	@ResponseBody
    public void sendMessageToKafkaTopic(@RequestBody MyHome myHome) {
        this.producer.sendMessage(myHome);
    }

}
