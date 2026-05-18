package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getName, username)
        );
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在: " + username);
        }
        List<GrantedAuthority> grantedAuthorities = List.of(new GrantedAuthorityImpl("ROLE_USER"));
        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
