package ru.saynurdinov.demo.forum.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDTO {
    private Long id;
    private String text;
    private LocalDateTime date;
    private Long userId;
}
