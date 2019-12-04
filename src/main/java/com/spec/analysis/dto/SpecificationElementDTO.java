package com.spec.analysis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationElementDTO {

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("sequence_number")
    private Integer sequenceNumber;

    @JsonProperty("element_title")
    private String elementTitle;

    @JsonProperty("specification_id")
    private Long specificationId;

    private String text;

    @JsonProperty("child_specification_Elements")
    private List<SpecificationElementDTO> childSpecificationElements;

}
