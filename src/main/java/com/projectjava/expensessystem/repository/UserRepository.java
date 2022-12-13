package com.projectjava.expensessystem.repository;

import com.projectjava.expensessystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserRepository findByEmailId(String email);

}
