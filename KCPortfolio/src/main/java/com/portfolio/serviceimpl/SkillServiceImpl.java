package com.portfolio.serviceimpl;

import com.portfolio.dto.SkillDTO;
import com.portfolio.entity.Skill;
import com.portfolio.mapper.SkillMapper;
import com.portfolio.repository.SkillRepository;
import com.portfolio.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }

    @Override
    public Map<String, List<Skill>> getSkillsGroupedByCategory() {
        return skillRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .collect(Collectors.groupingBy(skill -> skill.getCategory()));
    }

    @Override
    public SkillDTO createSkill(SkillDTO dto) {
        Skill saved = skillRepository.save(skillMapper.toEntity(dto));
        log.info("Skill created: {}", saved.getName());
        return skillMapper.toDTO(saved);
    }

    @Override
    public SkillDTO updateSkill(Long id, SkillDTO dto) {
        Skill existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        skillMapper.updateEntityFromDTO(dto, existing);  // maps into existing, preserves ID
        log.info("Skill updated: {}", existing.getName());
        return skillMapper.toDTO(skillRepository.save(existing));
    }

    @Override
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
        log.info("Skill deleted with id: {}", id);
    }
}