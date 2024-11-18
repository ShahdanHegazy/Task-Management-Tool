package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Entity.UserPrincipal;
import com.Academy.Task_Tool.Repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipal(user);
    }
}
