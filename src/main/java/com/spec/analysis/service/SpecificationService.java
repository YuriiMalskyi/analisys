package com.spec.analysis.service;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.dto.response.SingleObjectResponse;
import com.spec.analysis.dto.response.SpecificationNameMarkResponse;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.StudentSpecificationType;

import java.util.List;

public interface SpecificationService {

    void addSpecification(SpecificationDTO specificationDTO);

    void updateSpecification(SpecificationDTO specificationDTO);

    SpecificationDTO getSpecificationById(Long id);

    List<SpecificationNameMarkResponse> getSpecificationsByStudentIdAndType(
            Long id, StudentSpecificationType studentSpecificationType);

    List<SpecificationNameMarkResponse> getSpecificationsByStudentId(Long id);

    List<Specification> getStandards();

    List<SpecificationDTO> getStandardsDTO();

    SingleObjectResponse evaluateSpecification(Long standardSpecificationId, Long studentSpecificationId);

}
