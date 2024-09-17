package com.project.simpleapp.facade;

import java.util.List;

import com.project.simpleapp.api.request.UserRequest;
import com.project.simpleapp.api.response.UserFullResponse;
import com.project.simpleapp.api.response.UserShortResponse;

public interface UserFacade {

    Long createUser(UserRequest userRequest);

    UserFullResponse getUser(Long userId);

    List<UserShortResponse> getUsers();

    void deleteUser(Long userId);

    void updateUser(Long userId, UserRequest updatedUserRequest);
}
