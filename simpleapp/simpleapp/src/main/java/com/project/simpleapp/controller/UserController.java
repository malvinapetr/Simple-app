package com.project.simpleapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.simpleapp.api.request.UserRequest;
import com.project.simpleapp.api.response.UserFullResponse;
import com.project.simpleapp.api.response.UserShortResponse;
import com.project.simpleapp.facade.UserFacade;
import com.project.simpleapp.validator.UserValidator;

import jakarta.validation.ValidationException;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserFacade userFacade;
    private final UserValidator userValidator;

    @SuppressWarnings("null")
    @PostMapping
    public Long createUser(@RequestBody UserRequest userRequest, BindingResult result) {
        userValidator.validate(userRequest, result);
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldError().getDefaultMessage());
        }
        return userFacade.createUser(userRequest);
    }

    @SuppressWarnings("null")
    @PutMapping(path = "/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserRequest updatedUserRequest, BindingResult result) {
        userValidator.validate(updatedUserRequest, result);
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldError().getDefaultMessage());
        }
        userFacade.updateUser(userId, updatedUserRequest);
    }


    @GetMapping(path = "/{userId}")
    public UserFullResponse getUser(@PathVariable Long userId) {
        return userFacade.getUser(userId);
    }

    @GetMapping(path = "/all")
    public List<UserShortResponse> getUsers() {
        return userFacade.getUsers();
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userFacade.deleteUser(userId);
    }

}
