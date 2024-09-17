package com.project.simpleapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.project.simpleapp.domain.WorkAddress;

@Repository
public interface WorkAddressRepository extends JpaRepository<WorkAddress,Long> {

    Optional<WorkAddress> findByAddress(String address);

    @Modifying
    void deleteByAddress(String workAddress);
}
