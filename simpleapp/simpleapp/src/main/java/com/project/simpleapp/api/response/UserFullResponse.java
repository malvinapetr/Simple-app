package com.project.simpleapp.api.response;

import com.project.simpleapp.domain.enums.Gender;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserFullResponse {

    private String name;
    private String surname;
    private Gender gender;
    private String birthdate;
    private String workAddress;
    private String homeAddress;
}
