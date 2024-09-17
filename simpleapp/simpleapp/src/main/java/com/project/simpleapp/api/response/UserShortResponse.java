package com.project.simpleapp.api.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserShortResponse {

    private Long id;
    private String name;
    private String surname;
}
