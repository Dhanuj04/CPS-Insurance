package com.insurance.repository;


import com.insurance.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsModel, Long>{

}
