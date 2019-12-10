package com.spec.analysis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationElementDTO {

    @JsonProperty("sequence_number")
    private Integer sequenceNumber;

    @JsonProperty("element_title")
    private String elementTitle;

    @JsonProperty("specification_elements")
    private List<SpecificationElementDTO> specificationElements;

}
