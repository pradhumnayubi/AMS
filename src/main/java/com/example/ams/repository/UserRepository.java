package com.example.ams.repository;

import com.example.ams.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
//    @Query("SELECT u FROM User u WHERE u.apartment.apartmentId = :apartmentId")
//    List<User> findAllUsersInApartment(@Param("apartmentId") int apartmentId);

    List<User> findAllByUserIdIn(List<Integer> userIds);

}
