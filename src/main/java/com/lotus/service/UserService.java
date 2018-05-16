package com.lotus.service;

import java.util.List;

import com.lotus.model.User;

public interface UserService {
	int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUser();
}
