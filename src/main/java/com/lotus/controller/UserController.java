package com.lotus.controller;

import com.lotus.model.User;
import com.lotus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		List<User> user=userService.getAllUser();
		return user.toString();
	}
}
