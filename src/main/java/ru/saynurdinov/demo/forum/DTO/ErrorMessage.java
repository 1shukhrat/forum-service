package ru.saynurdinov.demo.forum.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorMessage {
    private String error;
    private LocalDateTime date;
    private int status;
}
