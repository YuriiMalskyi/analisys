package com.spec.analysis.dto.request;

import com.spec.analysis.dto.SpecificationElementDTO;
import com.spec.analysis.entity.SpecificationElement;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SpecificationRequest {

    private LocalDateTime createdAt;

    private Long userId;

    private String specificationName;

    private List<SpecificationElementDTO> specificationElementRequests;

}
