package com.spec.analysis.service;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.StudentSpecificationType;

import java.util.List;

public interface SpecificationService {

    void addSpecification(SpecificationDTO specificationDTO);

    void updateSpecification(SpecificationDTO specificationDTO);

    SpecificationDTO getSpecificationById(Long id);

    List<SpecificationDTO> getSpecificationsByStudentIdAndType(
            Long id, StudentSpecificationType studentSpecificationType);

    List<SpecificationDTO> getSpecificationsByStudentId(Long id);

    Specification getStandard();

    SpecificationDTO getStandardDTO();

}
