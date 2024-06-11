package com.hutech.tests3.Services;

import com.hutech.tests3.Entities.Role;
import com.hutech.tests3.Entities.User;
import com.hutech.tests3.Repositories.RoleRepository;
import com.hutech.tests3.Repositories.UserRepository;
import com.hutech.tests3.RequestEntities.RegisterUser;
import com.hutech.tests3.RequestEntities.RequestUser;
import com.hutech.tests3.RequestEntities.RequestUserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(String id) {
        return userRepository.findById(id).get();
    }
    public User CreateUser(RequestUser requestUser) {
        try {
            User user = new User();
            user.setUsername(requestUser.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(requestUser.getPassword()));
            user.setEmail(requestUser.getEmail());
            user.setFirstName(requestUser.getFirstName());
            user.setLastName(requestUser.getLastName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(requestUser.getBirthDate());
            user.setBirthDay(date);
            user.setEnabled(true);
            Role role = roleRepository.findOneByName("USER");
            user.setRole(role);
            return userRepository.save(user);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public User UpdateUser(RequestUserUpdate requestUserUpdate) {
        try {
            User user = userRepository.findById(requestUserUpdate.getId()).get();
            user.setFirstName(requestUserUpdate.getFirstName());
            user.setLastName(requestUserUpdate.getLastName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(requestUserUpdate.getBirthDay());
            user.setBirthDay(date);
            user.setPassword(requestUserUpdate.getPassword());
            user.setEmail(requestUserUpdate.getEmail());
            user.setRole(requestUserUpdate.getRole());
            return userRepository.save(user);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public User userRegister(RegisterUser registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setRole(roleRepository.findOneByName("USER"));
        return userRepository.save(user);
    }
}