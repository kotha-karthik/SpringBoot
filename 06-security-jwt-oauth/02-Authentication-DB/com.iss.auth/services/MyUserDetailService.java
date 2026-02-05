package com.iss.securitydb.service;

import com.iss.securitydb.models.UserPrincipal;
import com.iss.securitydb.models.Users;
import com.iss.securitydb.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users u1=userRepo.findByUsername(username);

        if(u1==null){
            System.out.println("Username not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserPrincipal(u1);
    }
}
