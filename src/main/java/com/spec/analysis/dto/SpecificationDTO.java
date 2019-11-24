package com.spec.analysis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationDTO {

    private String specificationName;

    private String description;

    @JsonFormat(pattern = "specification_type")
    private SpecificationType specificationType;

    @JsonFormat(pattern = "specification_elements")
    private List<SpecificationElement> specificationElements;

}
