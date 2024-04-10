package ru.saynurdinov.demo.forum.service;

import org.springframework.security.access.AccessDeniedException;
import ru.saynurdinov.demo.forum.DTO.EditMessageDTO;
import ru.saynurdinov.demo.forum.DTO.NewMessageDTO;
import ru.saynurdinov.demo.forum.entity.Message;


import java.util.List;
import java.util.NoSuchElementException;

public interface MessageService {
    List<Message> getAllByTopic(int page, long topicId) throws NoSuchElementException;
    Message add(NewMessageDTO newMessage, long topicId) throws NoSuchElementException;
    Message edit(EditMessageDTO editedMessage, long messageId) throws NoSuchElementException, AccessDeniedException;
    void delete(long messageId) throws NoSuchElementException, AccessDeniedException;
}
