package com.stock.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.User;
import com.stock.repo.UserRepo;
import com.stock.service.UserService;

@Service
public class UserImpl implements UserService{

	@Autowired
	UserRepo repo;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

}
