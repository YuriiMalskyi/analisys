package com.spec.analysis.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@NotNull
public class RegisterRequest {

    private String username;

    private String password;

    @JsonFormat(pattern = "first_name")
    private String firstName;

    @JsonFormat(pattern = "last_name")
    private String lastName;

    private Boolean isStudent;

}
