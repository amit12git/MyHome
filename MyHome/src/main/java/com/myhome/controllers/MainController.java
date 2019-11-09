/**
 * 
 */
package com.myhome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author amitabhsinha
 *
 */
@Controller
public class MainController {
	
	@RequestMapping("/")
	//@ResponseBody
	public String start(){
		
		return "hello.html";
		
	}

}
