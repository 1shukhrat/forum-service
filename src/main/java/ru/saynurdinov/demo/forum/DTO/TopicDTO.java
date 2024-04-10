package ru.saynurdinov.demo.forum.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopicDTO {
    private Long id;
    private String title;
}
