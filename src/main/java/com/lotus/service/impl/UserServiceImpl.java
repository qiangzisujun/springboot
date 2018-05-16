package com.lotus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lotus.model.Role;
import com.lotus.model.User;
import com.lotus.service.UserService;

import com.lotus.dao.UserMapper;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	 
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userMapper.getAllUser();
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userMapper.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        List<Role> roles = user.getRoles();
		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority(role.getName()));
		}     
        org.springframework.security.core.userdetails.User auth_user = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),list);
        System.out.println("s:"+name);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
	}

}
