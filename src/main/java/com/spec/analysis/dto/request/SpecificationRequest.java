package com.spec.analysis.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spec.analysis.dto.SpecificationElementDTO;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SpecificationRequest {

    private Long userId;

    @JsonProperty("specification_name")
    private String specificationName;

    private String description;

    @JsonProperty("specification_type")
    private SpecificationType specificationType;

    @JsonProperty("specification_elements")
    private List<SpecificationElementDTO> specificationElementRequests;

}
