package ru.saynurdinov.demo.forum.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private String role;
}
