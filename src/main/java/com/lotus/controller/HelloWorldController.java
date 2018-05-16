package com.lotus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/index")
public class HelloWorldController {
	
	@RequestMapping(value = "/demo")
	public String helloWorld() {
		return "helloworld";
	}
}
