package com.project.simpleapp.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
public class HomeAddress extends BaseEntity {

    private String address;

    @Builder.Default
    @OneToMany(mappedBy = "homeAddress", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
}
