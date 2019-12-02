package com.spec.analysis.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    @JsonFormat(pattern = "first_name")
    private String firstName;

    @JsonFormat(pattern = "last_name")
    private String lastName;

}
