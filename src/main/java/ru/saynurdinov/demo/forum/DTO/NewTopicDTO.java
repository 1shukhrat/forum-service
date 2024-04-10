package ru.saynurdinov.demo.forum.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewTopicDTO {
    @NotBlank(message = "topic's title must be not blank")
    @Size(min=3, message = "min length topic's title must be 3")
    private String title;
    @NotNull
    private NewMessageDTO message;
}
