package com.spec.analysis.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NotNull
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Long id;

    private String username;

}
