package com.team2.mealPlanner.services;


import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.Role;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImp implements UserService {


    @Autowired
    private UserDao userDao;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User save(User user)
    {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userDao.addUser(user);
    }


    public User findUserByLogin(String userName, String password)
    {
        return userDao.findUserByLogin(userName);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findUserByLogin(s);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<Role> list  = new LinkedList<>();
        list.add(new Role("USER"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(list));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
