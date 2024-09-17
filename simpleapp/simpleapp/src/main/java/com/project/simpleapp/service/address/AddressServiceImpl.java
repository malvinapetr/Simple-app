package com.project.simpleapp.service.address;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.WorkAddress;
import com.project.simpleapp.repository.HomeAddressRepository;
import com.project.simpleapp.repository.WorkAddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final HomeAddressRepository homeAddressRepository;
    private final WorkAddressRepository workAddressRepository;
    
    @Override
    public Optional<WorkAddress> findByWorkAddress(String workAddress) {
        return workAddressRepository.findByAddress(workAddress);
    }

    @Override
    public Optional<HomeAddress> findByHomeAddress(String homeAddress) {
        return homeAddressRepository.findByAddress(homeAddress);
    }

    @Override
    public void deleteByWorkAddress(String workAddress) {
        workAddressRepository.deleteByAddress(workAddress);
    }

    @Override
    public void deleteByHomeAddress(String homeAddress) {
        homeAddressRepository.deleteByAddress(homeAddress);
    }
}
