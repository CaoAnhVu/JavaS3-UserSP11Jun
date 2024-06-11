package com.hutech.tests3.Services;

import com.hutech.tests3.Entities.CustomUserDetail;
import com.hutech.tests3.Entities.User;
import com.hutech.tests3.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        CustomUserDetail userDetails = new CustomUserDetail(user);
        System.out.println(userDetails.getAuthorities());
        return userDetails;
    }
    public List<GrantedAuthority> BuildRolesFromRole(String Role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roles = "ADMIN,MODIFIER,USER";
        int index = roles.indexOf(Role);
        String substring = roles.substring(index);
        String authString[] = substring.split(",");
        for (String auth : authString) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        return authorities;
    }
}
