package com.ecommers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
