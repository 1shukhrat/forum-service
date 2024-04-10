package ru.saynurdinov.demo.forum.service;

import ru.saynurdinov.demo.forum.DTO.EditTopicDTO;
import ru.saynurdinov.demo.forum.DTO.NewTopicDTO;
import ru.saynurdinov.demo.forum.entity.Topic;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.NoSuchElementException;

public interface TopicService {
    List<Topic> getAll(int page);
    Topic add(NewTopicDTO newTopic);
    Topic edit(EditTopicDTO editedTopic, long topicId) throws NoSuchElementException, AccessDeniedException;
    void delete(long topicId) throws NoSuchElementException, AccessDeniedException;
}
