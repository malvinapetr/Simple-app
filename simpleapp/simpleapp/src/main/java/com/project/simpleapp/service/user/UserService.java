package com.project.simpleapp.service.user;

import java.util.List;

import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.User;
import com.project.simpleapp.domain.WorkAddress;

public interface UserService {

    void deleteUser(Long userId);

    List<User> getUsers();

    User getUserById(Long userId);

    User saveUser(User user);

    int getCountUserWithHomeAddress(HomeAddress homeAddress);

    int getCountUserWithWorkAddress(WorkAddress workAddress);

}
