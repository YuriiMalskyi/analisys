package com.spec.analysis.service;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.entity.Specification;

import java.util.List;

public interface SpecificationService {

    void addSpecification(SpecificationDTO specificationDTO);

    void updateSpecification(SpecificationDTO specificationDTO);

    SpecificationDTO getSpecificationById(Long id);

    SpecificationDTO getSpecificationByStudentId(Long id);

    List<SpecificationDTO> getSpecificationsByStudentId(Long id);

    Specification getStandard();

    SpecificationDTO getStandardDTO();

}
