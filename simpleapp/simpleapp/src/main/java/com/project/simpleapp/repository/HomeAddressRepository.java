package com.project.simpleapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.project.simpleapp.domain.HomeAddress;

public interface HomeAddressRepository extends JpaRepository<HomeAddress,Long>{
    
    Optional<HomeAddress> findByAddress(String address);

    @Modifying
    void deleteByAddress(String homeAddress);
}
