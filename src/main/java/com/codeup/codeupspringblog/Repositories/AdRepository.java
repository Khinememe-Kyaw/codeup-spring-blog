package com.codeup.codeupspringblog.Repositories;

import com.codeup.codeupspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdRepository extends JpaRepository<Ad, Long> {

}
