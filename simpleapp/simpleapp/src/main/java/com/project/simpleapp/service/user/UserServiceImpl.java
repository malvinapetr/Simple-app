package com.project.simpleapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.User;
import com.project.simpleapp.domain.WorkAddress;
import com.project.simpleapp.repository.UserRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    
    @Override
    public int getCountUserWithWorkAddress(WorkAddress workAddress) {
        return userRepository.getCountUserWithWorkAddress(workAddress);
    }

    @Override
    public int getCountUserWithHomeAddress(HomeAddress homeAddress) {
        return userRepository.getCountUserWithHomeAddress(homeAddress);
    }

}
