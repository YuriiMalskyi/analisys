package com.spec.analysis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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

    private Long id;

    @JsonProperty("specification_name")
    private String specificationName;

    private String description;

    private Double mark;

    @JsonProperty("specification_type")
    private SpecificationType specificationType;

    @JsonProperty("specification_elements")
    private List<SpecificationElementDTO> specificationElements;

    @JsonProperty("standard_specification_id")
    private Long standardSpecificationId;
}
