package com.spec.analysis.service.impl;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.SpecificationService;
import com.spec.analysis.utils.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void addSpecification(SpecificationDTO specificationDTO) {

    }

    @Override
    public void updateSpecification(SpecificationDTO specificationDTO) {

    }

    @Override
    public SpecificationDTO getSpecificationById(Long id) {
        return null;
    }

    @Override
    public SpecificationDTO getSpecificationByStudentId(Long id) {
        return null;
    }

    @Override
    public List<SpecificationDTO> getSpecificationsByStudentId(Long id) {
        return null;
    }

    @Override
    public Specification getStandard() {
        Specification specification = specificationRepository.getBySpecificationType(SpecificationType.STANDARD_SPECIFICATION);
        System.out.println(specification);
        return specification;
    }

    @Override
    public SpecificationDTO getStandardDTO() {
        Specification specification = specificationRepository.getBySpecificationType(SpecificationType.STANDARD_SPECIFICATION);
        System.out.println(specification);
        SpecificationDTO specificationDTO = mapper.map(specification, SpecificationDTO.class);
        System.out.println(specificationDTO);
        return specificationDTO;
    }

}
