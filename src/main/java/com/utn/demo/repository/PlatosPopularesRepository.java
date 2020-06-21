package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.PlatosPopulares;

@Repository
public interface PlatosPopularesRepository extends JpaRepository<PlatosPopulares, Long> {

}
