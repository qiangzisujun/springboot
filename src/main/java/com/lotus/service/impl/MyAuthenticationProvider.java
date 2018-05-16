package com.lotus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lotus.dao.UserMapper;
import com.lotus.model.Role;
import com.lotus.model.User;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		User user=userMapper.findByUsername(username);
		if (user==null) {
			logger.error("{} login failed,username or password is wrong",username);
            throw new BadCredentialsException("Username or password is not correct");
		}// 加密过程在这里体现
		/*if (!password.equals(user.getPassword())) {
			System.out.print("密码错误");
			throw new BadCredentialsException("密码错误");
		}*/
		final List<GrantedAuthority> gAuthorities=new ArrayList<>();
		for(Role role :user.getRoles()){
			gAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		
		// 用户信息有效时将其放入 session 中
        session.setAttribute("user", user);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, gAuthorities);
        return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
