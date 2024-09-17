package com.project.simpleapp.facade;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.simpleapp.api.request.UserRequest;
import com.project.simpleapp.api.response.UserFullResponse;
import com.project.simpleapp.api.response.UserShortResponse;
import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.User;
import com.project.simpleapp.domain.WorkAddress;
import com.project.simpleapp.mapper.MapperRegistry;
import com.project.simpleapp.service.address.AddressService;
import com.project.simpleapp.service.user.UserService;

import jakarta.transaction.Transactional;
import lombok.*;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final MapperRegistry mapperRegistry;
    private final AddressService addressService;
    
    @Override
    @Transactional
    public Long createUser(UserRequest userRequest) {
        User user = mapperRegistry.getMapper(UserRequest.class, User.class).map(userRequest);
        return userService.saveUser(user).getId();
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserRequest updatedUserRequest) {
        User existingUser = userService.getUserById(userId);
        if(existingUser != null){
            User updatedUser = mapperRegistry.getMapper(UserRequest.class, User.class).map(updatedUserRequest);
            WorkAddress previousWorkAddress = existingUser.getWorkAddress();
            HomeAddress previousHomeAddress = existingUser.getHomeAddress();

            BeanUtils.copyProperties(updatedUser, existingUser, "id");
            if(previousWorkAddress != updatedUser.getWorkAddress()){
                if(userService.getCountUserWithWorkAddress(previousWorkAddress) == 0)
                    addressService.deleteByWorkAddress(previousWorkAddress.getAddress());}

            if(previousHomeAddress != updatedUser.getHomeAddress()){
                if(userService.getCountUserWithHomeAddress(previousHomeAddress) == 0)
                    addressService.deleteByHomeAddress(previousHomeAddress.getAddress());}
        }
    }

    @Override
    public UserFullResponse getUser(Long userId) {
        User user = userService.getUserById(userId);
        if(user == null)
            return null;
        return mapperRegistry.getMapper(User.class, UserFullResponse.class).map(user);
    }

    @Override
    public List<UserShortResponse> getUsers() {
        List<User> users = userService.getUsers(); 
        if(users.isEmpty())
            return null;
        return users.stream()
                .map(user -> mapperRegistry.getMapper(User.class, UserShortResponse.class).map(user)).toList();
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {

        User user = userService.getUserById(userId);
        userService.deleteUser(userId);

        // delete work address or home address records that may be orphaned by the user deletion
        if(user != null)
        {if(user.getWorkAddress() != null){
            if(userService.getCountUserWithWorkAddress(user.getWorkAddress()) == 0)
                addressService.deleteByWorkAddress(user.getWorkAddress().getAddress());}

        if(user.getHomeAddress() != null){
            if(userService.getCountUserWithHomeAddress(user.getHomeAddress()) == 0)
                addressService.deleteByHomeAddress(user.getHomeAddress().getAddress());}
        }
    }

}
