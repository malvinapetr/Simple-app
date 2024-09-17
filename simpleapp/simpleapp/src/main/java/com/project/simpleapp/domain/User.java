package com.project.simpleapp.domain;

import com.project.simpleapp.domain.enums.Gender;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity{
    private String name;
    private String surname;
    private Gender gender;
    private String birthdate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
    @JoinColumn(name = "work_address_id")
    private WorkAddress workAddress;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
    @JoinColumn(name = "home_address_id")
    private HomeAddress homeAddress;

}
