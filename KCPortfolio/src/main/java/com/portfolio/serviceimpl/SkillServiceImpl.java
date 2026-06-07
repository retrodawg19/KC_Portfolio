package com.portfolio.serviceimpl;

import com.portfolio.entity.Skill;
import com.portfolio.repository.SkillRepository;
import com.portfolio.service.SkillService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SkillServiceImpl implements SkillService {

    private  SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }

    // Returns skills grouped by category: { "Backend": [...], "Frontend": [...] }
    public Map<String, List<Skill>> getSkillsGroupedByCategory() {
        return skillRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .collect(Collectors.groupingBy(skill -> skill.getCategory()));
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill existing = getSkillById(id);
        updatedSkill.setId(existing.getId());
        return skillRepository.save(updatedSkill);
    }

    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
}