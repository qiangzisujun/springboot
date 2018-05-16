package com.lotus.service;

import com.lotus.model.User;

import java.util.List;

public interface UserService {
	int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUser();
}
