package com.myhome.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.mysql.MyHome;
import com.myhome.mysql.MyHomeRepository;

@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	 MyHomeRepository myHomeRepository;
	
	@RequestMapping("/")
	public String start(){
		return "hello.html";
	}
	
	@RequestMapping("/healthcheck")
	@ResponseBody
	public String getHealthCheck() {
		return "isWorking";
	}
	
	@RequestMapping(value ="/myhomeservice",method = RequestMethod.GET)
	@ResponseBody
	public List<MyHome> getMyHomeDetail()
	 {
	  Iterable<MyHome> result = myHomeRepository.findAll();
	  List<MyHome> homeList = new ArrayList<MyHome>();
	  result.forEach(homeList::add);
	  return homeList;
	 }
	
	
	
	@RequestMapping(value ="/myhomeservice",method = RequestMethod.POST)
	@ResponseBody
	 public MyHome addHomeDetail(@RequestBody MyHome myHome)
	 {
	 // String id = String.valueOf(new Random().nextInt());
	  MyHome home = new MyHome(myHome.getName(), myHome.getAge(), myHome.getJob());
	  myHomeRepository.save(home);
	  return home;
	 }

}
