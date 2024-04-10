package ru.saynurdinov.demo.forum.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterDTO {

    @NotBlank
    @Size(min = 3, message = "min length of username must be 3")
    private String username;

    @NotBlank
    @Size(min = 8, message = "min length of password must be 8")
    private String password;

    @Pattern(regexp = "^(ADMIN|USER)$", message = "Invalid role (ADMIN or USER should be)")
    private String role;

}
