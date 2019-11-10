/**
 * 
 */
package com.myhome.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
	@RequestMapping(value ="/myhome",method = RequestMethod.GET)
	@ResponseBody
	public List<MyHome> getMyHomeDetail()
	 {
	  Iterable<MyHome> result = myHomeRepository.findAll();
	  List<MyHome> employeesList = new ArrayList<MyHome>();
	  result.forEach(employeesList::add);
	  return employeesList;
	 }
	
	@RequestMapping(value ="/myhome/{id}",method = RequestMethod.PUT)
	@ResponseBody
	 public Optional<MyHome> updateEmployee(@RequestBody MyHome myHome, @PathVariable String id)
	 {
	  Optional<MyHome> optionalhome = myHomeRepository.findById(id);
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
	  String id = String.valueOf(new Random().nextInt());
	  MyHome emp = new MyHome(id, myHome.getName(), myHome.getAge(), myHome.getJob());
	  myHomeRepository.save(emp);
	  return emp;
	 }
	

}
