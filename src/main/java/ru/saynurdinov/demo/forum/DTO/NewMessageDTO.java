package ru.saynurdinov.demo.forum.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewMessageDTO {
    @NotBlank(message = "message's text must be not blank")
    private String text;
}
