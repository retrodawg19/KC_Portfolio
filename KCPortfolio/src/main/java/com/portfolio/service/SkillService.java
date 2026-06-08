package com.portfolio.service;

import java.util.List;
import java.util.Map;

import com.portfolio.dto.SkillDTO;
import com.portfolio.entity.Skill;

public interface SkillService {

	List<Skill> getAllSkills();

	Map<String, List<Skill>> getSkillsGroupedByCategory();

	SkillDTO createSkill(SkillDTO dto);

	SkillDTO updateSkill(Long id, SkillDTO dto);

	void deleteSkill(Long id);

}
