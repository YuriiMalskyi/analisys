package com.spec.analysis.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SpecificationNameMarkResponse {

    @JsonFormat(pattern = "specification_name")
    private String specificationName;

    private double mark;

}
