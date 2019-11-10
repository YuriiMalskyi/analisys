package com.spec.analysis.dto.request;

import com.spec.analysis.utils.validators.Password;
import com.spec.analysis.utils.validators.Username;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

//    @Username
    @NotNull
    private String username;

//    @Password
    @NotNull
    private String password;

}
