package com.tracking.panel.service;

import com.tracking.panel.domain.User;
import com.tracking.panel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String email){
        return userRepository.findOneByEmail(email);
    }


}
