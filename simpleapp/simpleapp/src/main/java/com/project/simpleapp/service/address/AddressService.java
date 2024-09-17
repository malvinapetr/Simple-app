package com.project.simpleapp.service.address;

import java.util.Optional;

import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.WorkAddress;

public interface AddressService {

    Optional<WorkAddress> findByWorkAddress(String workAddress);

    Optional<HomeAddress> findByHomeAddress(String homeAddress);

    void deleteByWorkAddress(String workAddress);

    void deleteByHomeAddress(String homeAddress);
}
