package com.example.springbootWithPostgresql.service.impl;

import com.example.springbootWithPostgresql.entity.UserEntity;
import com.example.springbootWithPostgresql.repository.UserRepository;
import com.example.springbootWithPostgresql.service.UserService;

import logs.LogController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long userId) {
        List<UserEntity> userOpt = userRepository.findAll();
        
        List<UserEntity> uE= userOpt.stream().filter(x -> x.getId()==userId).collect(Collectors.toList());        
        
        if(uE.size()>0)
        	return uE.get(0);
        else {
        	LogController.log("user not found");
            throw new RuntimeException("user not found.");

        }
        	
    }

    @Override
    public void saveUser(UserEntity user) {
        UserEntity userDetail = userRepository.save(user);
        System.out.println("user saved to db with userId : " + userDetail.getId());
    }

    @Override
    public void updateUser(UserEntity user, Long userId) {
    	try {
        Optional<UserEntity> userDetailOpt = userRepository.findById(userId);
        if(userDetailOpt.isPresent()){
            UserEntity userDetail = userDetailOpt.get();
            if(user.getName() != null || user.getName().isEmpty())
                userDetail.setName(user.getName());
            if(user.getDescription() != null || user.getDescription().isEmpty())
                userDetail.setDescription(user.getDescription());
                userDetail.setPrice(user.getPrice());
            userRepository.save(userDetail);
        }else{
            throw new RuntimeException("user not found.");
        }
        
    	}catch(Exception e) {
			LogController.logException(e);
			
		}
    }

    @Override
    public void deleteUseryId(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent())
            userRepository.deleteById(userId);
        else
            throw new RuntimeException("user not found.");
        
    }
}
