package com.spec.analysis.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationNameMarkResponse {

    private Long id;

    @JsonProperty("specification_name")
    private String specificationName;

    private double mark;

    private Long standardSpecificationId;
}
