/**
 * 
 */
package com.myhome.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import com.myhome.cassandra.MyHome;
import com.myhome.cassandra.MyHomeRepository;

/**
 * @author amitabhsinha
 *
 */
@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	 MyHomeRepository myHomeRepository;
	
	@Autowired
	ControllerUtily controllerUtily;
	
	@RequestMapping("/")
	public String start(){
		return "hello.html";
	}
	
	@RequestMapping("/healthcheck")
	@ResponseBody
	public String getHealthCheck() {
		return "isWorking";
	}
	
	@RequestMapping(value ="/myhome",method = RequestMethod.GET)
	@ResponseBody
	public List<MyHome> getMyHomeDetail()
	 {
	  Iterable<MyHome> result = myHomeRepository.findAll();
	  List<MyHome> homeList = new ArrayList<MyHome>();
	  result.forEach(homeList::add);
	  return homeList;
	 }
	
	@RequestMapping(value ="/myhome/{id}",method = RequestMethod.PUT)
	@ResponseBody
	 public Optional<MyHome> updateEmployee(@RequestBody MyHome myHome, @PathVariable String id)
	 {
	  Optional<MyHome> optionalhome = myHomeRepository.findById(id);
	  logger.info(controllerUtily.readIdDate(id));
	  if (optionalhome.isPresent()) {
	  
		   MyHome home = optionalhome.get();
		   home.setId(myHome.getId());
		   home.setName(myHome.getName());
		   home.setAge(myHome.getAge());
		   home.setJob(myHome.getJob());
		   myHomeRepository.save(home);
	  
	  }
	  return optionalhome;
	 }
	
	@RequestMapping(value ="/myhome",method = RequestMethod.POST)
	@ResponseBody
	 public MyHome addEmployee(@RequestBody MyHome myHome)
	 {
	 // String id = String.valueOf(new Random().nextInt());
	  MyHome home = new MyHome(myHome.getId(), myHome.getName(), myHome.getAge(), myHome.getJob());
	  myHomeRepository.save(home);
	  controllerUtily.writeDateToHazelCast(home);
	  return home;
	 }
	
	

	

}
