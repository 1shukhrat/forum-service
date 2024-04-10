package ru.saynurdinov.demo.forum.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopicWithMessagesDTO {
    private TopicDTO topicDTO;
    private List<MessageDTO> messages;
}
