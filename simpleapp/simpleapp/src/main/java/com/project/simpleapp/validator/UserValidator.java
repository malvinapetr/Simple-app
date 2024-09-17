package com.project.simpleapp.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.simpleapp.api.request.UserRequest;

import io.micrometer.common.util.StringUtils;

@Component
public class UserValidator implements Validator{
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return clazz.equals(UserRequest.class);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UserRequest user = (UserRequest) target;

        if (StringUtils.isBlank(user.getName())) {
            errors.rejectValue("name", "name.is.required", "Name is required");
        }

        if (StringUtils.isBlank(user.getSurname())) {
            errors.rejectValue("surname", "surname.is.required", "Surname is required");
        }

        if (user.getGender() == null) {
            errors.rejectValue("gender", "gender.is.required", "Gender is required");
        }

        if (StringUtils.isBlank(user.getBirthdate())) {
            errors.rejectValue("birthdate", "birthdate.is.required", "Birthdate is required");
        }

        if (!StringUtils.isBlank(user.getBirthdate())) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        
            try {
                LocalDate birthdate = LocalDate.parse(user.getBirthdate(),dtf);
                
                if (birthdate.isAfter(LocalDate.now())) {
                    errors.rejectValue("birthdate", "birthdate.is.invalid", "Birthdate can't be after today's date");
                }

                if (birthdate.isBefore(LocalDate.of(1920, 1, 1))) {
                    errors.rejectValue("birthdate", "birthdate.is.invalid", "Birthdate can't be before January 1, 1920");
                }

            } catch (DateTimeParseException e) {
                errors.rejectValue("birthdate", "birthdate.is.invalid", "Birthdate has invalid form");
            }
        }
    }
}
