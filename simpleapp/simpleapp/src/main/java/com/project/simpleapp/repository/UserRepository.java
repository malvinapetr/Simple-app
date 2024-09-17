package com.project.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.simpleapp.domain.HomeAddress;
import com.project.simpleapp.domain.User;
import com.project.simpleapp.domain.WorkAddress;

@Repository
public interface UserRepository extends JpaRepository<User,Long>  {

    @Query("SELECT COUNT(u) FROM User u WHERE u.homeAddress = :homeAddress")
    int getCountUserWithHomeAddress(@Param("homeAddress") HomeAddress homeAddress);

    @Query("SELECT COUNT(u) FROM User u WHERE u.workAddress = :workAddress")
    int getCountUserWithWorkAddress(@Param("workAddress") WorkAddress workAddress);
}
