package com.codeup.codeupspringblog.Repositories;


import com.codeup.codeupspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}