package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill,Long>{

	List<Skill> findAllByOrderByDisplayOrderAsc();

}
