package cn.thoughtworks.twMall.service;

import cn.thoughtworks.twMall.entity.BasicUserDetail;
import cn.thoughtworks.twMall.entity.User;
import cn.thoughtworks.twMall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwMallUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElse(null);
        return new BasicUserDetail(user);
    }
}
