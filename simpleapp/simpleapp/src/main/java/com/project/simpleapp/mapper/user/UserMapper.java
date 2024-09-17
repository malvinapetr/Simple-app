package com.project.simpleapp.mapper.user;

import org.springframework.stereotype.Component;

import com.project.simpleapp.api.request.UserRequest;
import com.project.simpleapp.api.response.UserFullResponse;
import com.project.simpleapp.api.response.UserShortResponse;
import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.User;
import com.project.simpleapp.domain.WorkAddress;
import com.project.simpleapp.mapper.Mapper;
import com.project.simpleapp.mapper.MapperRegistry;
import com.project.simpleapp.service.address.AddressService;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Nonnull
    private final MapperRegistry mapperRegistry;
    private final AddressService addressService;


    @PostConstruct
    private void registerMappers() {
        mapperRegistry.addMapper(UserRequest.class, User.class, userRequestUserMapper());
        mapperRegistry.addMapper(User.class, UserFullResponse.class, userUserFullResponseMapper());
        mapperRegistry.addMapper(User.class, UserShortResponse.class, userUserShortResponseMapper());
    }

    private Mapper<UserRequest, User> userRequestUserMapper() {
        return userRequest -> {
            WorkAddress workAddress = null;
            if(!StringUtils.isBlank(userRequest.getWorkAddress()))
            {workAddress = addressService.findByWorkAddress(userRequest.getWorkAddress())
                .orElse(WorkAddress.builder()
                    .address(userRequest.getWorkAddress())
                    .build());
            }

            HomeAddress homeAddress = null;
            if(!StringUtils.isBlank(userRequest.getHomeAddress()))
            {homeAddress = addressService.findByHomeAddress(userRequest.getHomeAddress())
                .orElse(HomeAddress.builder()
                    .address(userRequest.getHomeAddress())
                    .build());
            }

            return User
                .builder()
                .name(userRequest.getName()) 
                .surname(userRequest.getSurname())
                .birthdate(userRequest.getBirthdate())
                .gender(userRequest.getGender())
                .workAddress(workAddress)
                .homeAddress(homeAddress)
                .build();
        };
    }

    private Mapper<User, UserFullResponse> userUserFullResponseMapper() {
        return user -> {
            return UserFullResponse
            .builder()
            .name(user.getName())
            .surname(user.getSurname())
            .birthdate(user.getBirthdate())
            .gender(user.getGender())
            .workAddress(user.getWorkAddress() == null ? "N/A" : user.getWorkAddress().getAddress())
            .homeAddress(user.getHomeAddress() == null ? "N/A" : user.getHomeAddress().getAddress())
            .build();
        };
    }

    private Mapper<User, UserShortResponse> userUserShortResponseMapper() {
        return user -> {
            return UserShortResponse
            .builder()
            .id(user.getId())
            .name(user.getName())
            .surname(user.getSurname())
            .build();
        };
    }

}
