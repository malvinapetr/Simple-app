package com.project.simpleapp.api.request;

import com.project.simpleapp.domain.enums.Gender;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class UserRequest {
    private String name;
    private String surname;
    private Gender gender;
    private String birthdate;
    private String workAddress;
    private String homeAddress;
}
