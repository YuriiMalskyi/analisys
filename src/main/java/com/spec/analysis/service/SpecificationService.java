package com.spec.analysis.service;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.dto.response.ObjectResponse;
import com.spec.analysis.dto.response.SpecificationNameMarkResponse;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.StudentSpecificationType;

import java.util.List;

public interface SpecificationService {

    void addSpecification(Long userId, SpecificationDTO specificationDTO);

    void updateSpecification(SpecificationDTO specificationDTO);

    SpecificationDTO getSpecificationById(Long id);

    List<SpecificationNameMarkResponse> getSpecificationsByStudentIdAndType(
            Long id, StudentSpecificationType studentSpecificationType);

    List<SpecificationNameMarkResponse> getSpecificationsByStudentId(Long id);

    SpecificationDTO getStandardDTO(Long specificationId);

    Long getStandardSpecificationIdByStudentSpecificationId(Long studentSpecificationId);

    List<Specification> getStandards();

    List<SpecificationDTO> getStandardsDTO();

    ObjectResponse<Double> evaluateSpecification(Long studentSpecificationId);

}
