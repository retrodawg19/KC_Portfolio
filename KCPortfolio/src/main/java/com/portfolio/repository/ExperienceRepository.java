package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Long>{

	List<Experience> findAllByOrderByStartDateDesc();

}
