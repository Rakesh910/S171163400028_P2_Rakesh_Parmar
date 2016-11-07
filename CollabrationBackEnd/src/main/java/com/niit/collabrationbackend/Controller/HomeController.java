package com.niit.collabrationbackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String getIndexPage(){
		System.out.println("Index Controller");
		return "HomePage/index";
	}
	
	/*@RequestMapping(value="/register",method = RequestMethod.GET)
	public String getRegisterPage(){
		System.out.println("Index Controller2");
		return "UserPages/Registration";
	}
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String getHomePage(){
		System.out.println("Index Controller3");
		return "HomePage/index";
	}*/
}
