package com.spec.analysis.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SpecificationElementDTO {

    private LocalDateTime createdAt;

    private Integer sequenceNumber;

    private String elementTitle;

    private Long specificationId;

    private String text;

    private List<SpecificationElementDTO> childSpecificationElements;

}
